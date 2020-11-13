package com.xingyu.travel.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:
 * @Author: Xingyu
 * @PackageName: com.xingyu.travel.util
 * @Version: 1.0
 **/
public class UploadFileUtil {
	//下面是uploadFileByFiles方法
	public static String uploadFileByFiles(MultipartFile[] files) throws Exception {
		String fileNameStr = "";
		for (int i = 0; i < files.length; i++) { //对所有文件依次获取
			// 取得上传文件
			if (files[i] != null) {
				// 取得当前上传文件的文件名称
				String myFileName = files[i].getOriginalFilename();
				// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
				if (myFileName.trim() != "") {
					String fileTyps = myFileName.substring(myFileName.lastIndexOf("."));
					// String tempName="demo"+fileTyps;
					String tempName = UUID.randomUUID().toString().replace("-", "") + fileTyps;
					// 创建文件夹
					String folderName = folderName();
					String folderPath = "G:\\WorkSpace\\WorkSpace_IDEA\\WorkSpace_JavaFrame\\Travel\\src\\main\\webapp\\static\\images";
					//创建文件
					File uploadFile = new File(folderPath + File.separator + tempName);
					//上传文件
					files[i].transferTo(uploadFile);
					if (fileNameStr == "") {
						fileNameStr = tempName;
					} else {
						fileNameStr = fileNameStr + ";" + tempName;
					}
				}
			}
		}
		return fileNameStr;
	}

	/**
	 * 当前日期当文件夹名
	 *
	 * @return
	 */
	public static String folderName() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(new Date());
		return str;
	}
}
