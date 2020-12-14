/**
 * 
 */
package com.review.business.util;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.review.business.FileEntryBusiness;
import com.review.model.FileEntry;
import com.review.util.BeanUtil;
import com.review.util.UserContext;

/**
 * @author ddung
 *
 */
public class FileEntryBusinessFactoryUtil {
	// Design pattern - Singleton
	private static FileEntryBusiness _fileEntryBusiness;

	public static FileEntryBusiness getFileEntryBusiness() {

		if (_fileEntryBusiness == null) {
			_fileEntryBusiness = BeanUtil.getBean(FileEntryBusiness.class);
		}
		return _fileEntryBusiness;
	}
	// ============================

	public static FileEntry findById(long fileEntryId, UserContext userContext) {
		return getFileEntryBusiness().findById(fileEntryId, userContext);
	}

	public static List<FileEntry> createFileEntry(MultipartFile[] files, String className, long classPK,
			UserContext userContext) throws IOException {
		return getFileEntryBusiness().createFileEntry(files, className, classPK, userContext);
	}

	public static List<FileEntry> updateFileEntry(MultipartFile[] files, String className, long classPK,
			UserContext userContext) throws IOException {
		return getFileEntryBusiness().updateFileEntry(files, className, classPK, userContext);
	}

	public static String getPathImage(long fileEntryId) {
		return getFileEntryBusiness().getPathImage(fileEntryId);
	}

	public static List<String> findByClassName_ClassPK(String className, long classPK) {
		return getFileEntryBusiness().findByClassName_ClassPK(className, classPK);
	}
}
