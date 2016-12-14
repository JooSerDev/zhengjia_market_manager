package com.joosure.manager.mvc.wechat.util;

import static com.shawn.server.core.util.EncryptUtil.byte2hex;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.joosure.server.mvc.wechat.constant.CommonConstant;
import com.shawn.server.core.util.EncryptUtil;

public class ManagerUtils {

	private static final String KEY_SHA_256 = "SHA-256";
	
	/**
	 * 帖子编号后缀
	 */
	private static final String SUFFIX_NOTE = "nt";
	/**
	 * 文章编号后缀
	 */
	private static final String SUFFIX_ARTICLE = "at";
	
	/**
	 * 发送短信的接口
	 */
	public static final String SMS_POST_URL = "http://www.jianzhou.sh.cn/JianzhouSMSWSServer/http/sendBatchMessage";
	
	private static Logger log = Logger.getLogger(ManagerUtils.class);

	/**
	 * SHA-256 加密
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encryptBySHA256(String data) throws NoSuchAlgorithmException{
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA_256);
		sha.update(data.getBytes());
		return byte2hex(sha.digest());
	}

	public static String encryptBySHA256(String data,String salt) throws NoSuchAlgorithmException{
		salt = salt == null ? "":salt;
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA_256);
		sha.update((data+salt).getBytes());
		return byte2hex(sha.digest());
	}
	
	public static String getDefaultPass(String salt){
		try {
			return encryptBySHA256(CommonConstant.PASSWORD,salt);
		} catch (NoSuchAlgorithmException e) {
			log.error("getDefaultPass error:"+e.getMessage());
		}
		return "";
	}
	
	/**
	 * 对申请名称 和 时间戳  先进行 base64加密，然后进行sha-1 加密
	 * @param applyName
	 * @return
	 */
	public static String encryptAppId(String applyName){
		String encryptStr = applyName+new Date().getTime();
		String base64Str = EncryptUtil.encryptBASE64(encryptStr.getBytes());
		try {
			return EncryptUtil.encryptSHA(base64Str).toString();
		} catch (Exception e) {
			log.error("encryptAppId error:"+e.getMessage());
		}
		return null;
	}
	
	/**
	 * 传入一个时间，获取该时间当天的 0 点 和 24点
	 * @param cal
	 * @return
	 */
	public static List<Date> getDefaultBETime(Calendar cal){
		List<Date> date = new ArrayList<Date>();
		if(cal == null){
			cal = Calendar.getInstance();
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND, 0);
		Date startTime = cal.getTime();//开始时间
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE,59);
		cal.set(Calendar.SECOND, 59);
		Date endTime = cal.getTime();//结束时间
		date.add(startTime);
		date.add(endTime);
		return date;
	}
	
	/**
	 * 获取n天前 的  00:00:00  到昨天的  23:59:59  两个时间点
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午2:57:25
	 * @param cal
	 * @return
	 */
	public static List<Date> getDayToTodayDates(int num){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1-num);
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDate = cal.getTime();
		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate);
		dates.add(endDate);
		return dates;
	}
	
	/**
	 * 获取 某日期的 当天 00:00:00  到  n 天后  23:59:59
	 * @author Ted-wuhuhu
	 * @Time 2016年12月7日 下午3:13:07
	 * @param cal
	 * @param num
	 * @return
	 */
	public static List<Date> getDayToDay(Calendar cal,int num){
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, num);
		cal.set(Calendar.HOUR_OF_DAY,23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		Date endDate = cal.getTime();
		List<Date> dates = new ArrayList<Date>();
		dates.add(startDate);
		dates.add(endDate);
		return dates;
	}
	
	public static String generateIdForNote(boolean flag){
		long timestamp = System.currentTimeMillis();
		String uuid = UUID.randomUUID().toString();
		String prefix = uuid.substring(0,4)+timestamp;
		if(flag){
			return prefix+SUFFIX_NOTE;//帖子  note
		}else{
			return prefix+SUFFIX_ARTICLE;//文章  article
		}
	}
	
	public static void main(String[] args) {
		try {
			String secrectPass = encryptBySHA256(CommonConstant.PASSWORD, "9999");
			System.out.println(secrectPass);
			String secrectPass1 = encryptBySHA256(CommonConstant.PASSWORD, "ce97d620-79f1-411c-ac57-c6dd6c513364");
			System.out.println(secrectPass1);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成审核通过与不通过的短信模板
	 * @author Ted-wuhuhu
	 * @Time 2016年12月8日 下午2:32:20
	 * @param approvalFlag
	 * @param month
	 * @param day
	 * @param mobile
	 * @return
	 */
	public static String generateEnterApprovalMsg(boolean approvalFlag,int month,int day,String mobile){
		StringBuffer sb = new StringBuffer();
		sb.append("尊敬的分享市集用户,");
		if(approvalFlag){
			//通过审核
			sb.append("恭喜您申请的"+month+"月"+day+"日线下排队摊主名额已通过审核，客服可能将通过电话与您联系，请保持通讯畅通");
		}else{
			//审核拒绝
			sb.append("很抱歉您申请的"+month+"月"+day+"日线下排队摊主名额未能通过审核,请下期再做申请");
		}
		return sb.toString();
	}
	
	/**
	 * 发送短信
	 * 
	 * @param mobile
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static boolean sendSMS(String mobile, String content) throws IOException {
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		try {
			URL postUrl = new URL(SMS_POST_URL);
			connection = (HttpURLConnection) postUrl.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			String smsContent = "account=" + "sdk_zhengjiagc" + "&" + "password=" + "28478376" + "&" + "sendDateTime="
					+ "" + "&" + "destmobile=" + mobile + "&" + "msgText="
					+ URLEncoder.encode(content + "【正佳广场】", "UTF-8");

			out.writeBytes(smsContent);
			out.flush();
			out.close(); // flush and close
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			if (!sb.toString().equals("")) {
				int temp = Integer.parseInt(sb.toString());
				if (temp > 0) {
					return true;
				}
			}
			return false;
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
	
	
}
