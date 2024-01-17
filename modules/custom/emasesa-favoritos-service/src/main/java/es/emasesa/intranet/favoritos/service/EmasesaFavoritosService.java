package es.emasesa.intranet.favoritos.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.theme.ThemeDisplay;

public interface EmasesaFavoritosService {
	
	 public boolean addFav(String classPK, long assetEntryClassId, long groupId, String title, String url , String fileExtension, String ddmStructureKey) throws PortalException;

	 public boolean deleteFav(String classPK) throws PortalException;

	 public boolean isFav(String classPK) throws PortalException;

	 public boolean deleteFavs(String classPK) throws PortalException;

	 public Hits searchFavoritesByUserAndType(ThemeDisplay themeDisplay, String classNameId,int start,int end) throws ParseException, SearchException;

	 public Hits searchFavoritesJournalsArticleByUserAndDDMStructureKey(ThemeDisplay themeDisplay, String ddmStructureKey,int start,int end) throws ParseException, SearchException;
}