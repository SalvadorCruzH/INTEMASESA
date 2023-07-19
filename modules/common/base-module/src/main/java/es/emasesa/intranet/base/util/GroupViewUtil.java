package es.emasesa.intranet.base.util;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.kernel.model.SocialActivitySet;
import com.liferay.social.kernel.service.SocialActivitySetLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.text.SimpleDateFormat;
import java.util.List;

@Component(
        immediate = true,
        service = GroupViewUtil.class
)
public class GroupViewUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public String getDateLastActivity(AssetEntry group){
        String date = StringPool.BLANK;

        SocialActivitySet activity = getSocialActivitySet(group.getClassPK());
        if(Validator.isNotNull(activity)){
            date =  sdf.format(activity.getCreateDate());
        }else{
            date = sdf.format(group.getModifiedDate());
        }

        return date;
    }

    public SocialActivitySet getSocialActivitySet(long classPK){
        List<SocialActivitySet> activities =  _socialActivitySetLocalService.getGroupActivitySets(
                classPK, 0, 1);
        SocialActivitySet activity = activities.stream().findFirst().orElse(null);
        return activity;
    }
    
    public String getDateLastActivity(Group group){
        String date = StringPool.BLANK;

        SocialActivitySet activity = getSocialActivitySet(group.getClassPK());
        if(Validator.isNotNull(activity)){
            date =  sdf.format(activity.getCreateDate());
        }else{
            date = sdf.format(group.getModifiedDate());
        }

        return date;
    }

    public String getLogo(ThemeDisplay themeDisplay,AssetEntry assetEntry){
        Group group = null;
        String logo = StringPool.BLANK;

        try {
            group = _groupLocalService.getGroup(assetEntry.getClassPK());
            logo = getLogo(themeDisplay,group);
        } catch (PortalException e) {
            throw new RuntimeException(e);
        }

        return logo;

    }

    public String getLogo(ThemeDisplay themeDisplay,Group group){
        String logo = StringPool.BLANK;

         logo = group.getLogoURL(themeDisplay, true);

        return logo;

    }

    public String getFriendlyURL(ThemeDisplay themeDisplay,AssetEntry assetEntry){
        String url = StringPool.BLANK;
        Group group = null;

        try {
            group = _groupLocalService.getGroup(assetEntry.getClassPK());
            url = getFriendlyURL(themeDisplay,group);

        } catch (PortalException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(),e);
        }

        return url;
    }

    public String getFriendlyURL(ThemeDisplay themeDisplay){
        String url = StringPool.BLANK;
        Group group = null;

            group = themeDisplay.getScopeGroup();
            url = getFriendlyURL(themeDisplay,group);

        return url;
    }

    public String getFriendlyURL(ThemeDisplay themeDisplay,Group group){
        String url = StringPool.BLANK;
        try {

            url = _portal.getLayoutSetFriendlyURL(group.getPrivateLayoutSet(),themeDisplay);

        } catch (PortalException e) {
            LOG.error(e.getMessage());
            LOG.debug(e.getMessage(),e);
        }

        return url;
    }
    @Reference
    SocialActivitySetLocalService _socialActivitySetLocalService;

    @Reference
    Portal _portal;
    @Reference
    GroupLocalService _groupLocalService;

    private static final Log LOG = LoggerUtil.getLog(GroupViewUtil.class);
}
