package VerteilterTerminKalender.i18n;

import java.util.ResourceBundle;

/**
 * Provides internationalised Strings for usage in the Graphical User Interface.
 * Used Resource File: Components
 *
 * @author Johannes Gerwert
 * @version 12.03.2019
 */
public abstract class I18nComponentsUtil {

    private static final String APPLICATION_NAME = "application-name";

    private static final String MENU_FILE = "menu-file";
    private static final String MENU_EDIT = "menu-edit";
    private static final String MENU_HELP = "menu-help";
    private static final String MENU_ACCOUNT = "menu-account";
    private static final String MENU_EVENT = "menu-event";
    private static final String MENU_INVITE = "menu-invite";

    private static final String MENUITEM_CLOSE = "menuitem-close";
    private static final String MENUITEM_LOGOFF = "menuitem-logoff";
    private static final String MENUITEM_NEW = "menuitem-new";
    private static final String MENUITEM_EDIT = "menuitem-edit";
    private static final String MENUITEM_DELETE = "menuitem-delete";
    private static final String MENUITEM_ABOUT = "menuitem-about";

    private static final String PREVIOUS_MONTH_BUTTON = "previous-month-button";
    private static final String NEXT_MONTH_BUTTON = "next-month-button";
    private static final String NEW_EVENT_BUTTON = "new-event-button";
    private static final String EDIT_EVENT_BUTTON = "edit-event-button";
    private static final String NEW_INVITE_BUTTON = "new-invite-button";

    private static final String BUTTON_ACCEPT_INVITE = "button-accept-invite";
    private static final String BUTTON_DECLINE_INVITE = "button-decline-invite";

    private static final String LABEL_MONDAY = "label-monday";
    private static final String LABEL_TUESDAY = "label-tuesday";
    private static final String LABEL_WEDNESDAY = "label-wednesday";
    private static final String LABEL_THURSDAY = "label-thursday";
    private static final String LABEL_FRIDAY = "label-friday";
    private static final String LABEL_SATURDAY = "label-saturday";
    private static final String LABEL_SUNDAY = "label-sunday";

    private static final String ACC_DESCRIPTION = "acc-description";
    private static final String ACC_TIME = "acc-time";
    private static final String ACC_TIME_SEPARATOR = "acc-time-separator";
    private static final String ACC_PLACE = "acc-place";
    private static final String ACC_OWNER = "acc-owner";
    private static final String ACC_PARTICIPANTS = "acc-participants";

    private static final String TITLED_PANE_INVITATIONS = "titled-pane-invitations";
    private static final String TITLED_PANE_EVENT = "titled-pane-event";


    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getComponentsResourceBundle();
    }


    public static String getApplicationName(){
        return resourceBundle.getString(APPLICATION_NAME);
    }



    public static String getMenuFile(){
        return resourceBundle.getString(MENU_FILE);
    }

    public static String getMenuEdit(){
        return resourceBundle.getString(MENU_EDIT);
    }

    public static String getMenuHelp(){
        return resourceBundle.getString(MENU_HELP);
    }

    public static String getMenuAccount(){
        return resourceBundle.getString(MENU_ACCOUNT);
    }

    public static String getMenuEvent(){
        return resourceBundle.getString(MENU_EVENT);
    }

    public static String getMenuInvite(){
        return resourceBundle.getString(MENU_INVITE);
    }



    public static String getMenuitemClose(){
        return resourceBundle.getString(MENUITEM_CLOSE);
    }

    public static String getMenuitemLogoff(){
        return resourceBundle.getString(MENUITEM_LOGOFF);
    }

    public static String getMenuitemNew(){
        return resourceBundle.getString(MENUITEM_NEW);
    }

    public static String getMenuitemEdit(){
        return resourceBundle.getString(MENUITEM_EDIT);
    }

    public static String getMenuitemDelete(){
        return resourceBundle.getString(MENUITEM_DELETE);
    }

    public static String getMenuitemAbout(){
        return resourceBundle.getString(MENUITEM_ABOUT);
    }




    public static String getPreviousMonthButton(){
        return resourceBundle.getString(PREVIOUS_MONTH_BUTTON);
    }

    public static String getNextMonthButton(){
        return resourceBundle.getString(NEXT_MONTH_BUTTON);
    }

    public static String getNewEventButton(){
        return resourceBundle.getString(NEW_EVENT_BUTTON);
    }

    public static String getEditEventButton(){
        return resourceBundle.getString(EDIT_EVENT_BUTTON);
    }

    public static String getNewInviteButton(){
        return resourceBundle.getString(NEW_EVENT_BUTTON);
    }




    public static String getButtonAcceptInvite(){
        return resourceBundle.getString(BUTTON_ACCEPT_INVITE);
    }

    public static String getButtonDeclineInvite(){
        return resourceBundle.getString(BUTTON_DECLINE_INVITE);
    }





    public static String getLabelMonday(){
        return resourceBundle.getString(LABEL_MONDAY);
    }

    public static String getLabelTuesday(){
        return resourceBundle.getString(LABEL_TUESDAY);
    }

    public static String getLabelWednesday(){
        return resourceBundle.getString(LABEL_WEDNESDAY);
    }

    public static String getLabelThursday(){
        return resourceBundle.getString(LABEL_THURSDAY);
    }

    public static String getLabelFriday(){
        return resourceBundle.getString(LABEL_FRIDAY);
    }

    public static String getLabelSaturday(){
        return resourceBundle.getString(LABEL_SATURDAY);
    }

    public static String getLabelSunday(){
        return resourceBundle.getString(LABEL_SUNDAY);
    }




    public static String getAccDescription(){
        return resourceBundle.getString(ACC_DESCRIPTION);
    }

    public static String getAccTime(){
        return resourceBundle.getString(ACC_TIME);
    }

    public static String getAccTimeSeparator(){
        return resourceBundle.getString(ACC_TIME_SEPARATOR);
    }

    public static String getAccPlace(){
        return resourceBundle.getString(ACC_PLACE);
    }

    public static String getAccOwner(){
        return resourceBundle.getString(ACC_OWNER);
    }

    public static String getAccParticipants(){
        return resourceBundle.getString(ACC_PARTICIPANTS);
    }




    public static String getTitledPaneInvitations(){
        return resourceBundle.getString(TITLED_PANE_INVITATIONS);
    }

    public static String getTitledPaneEvent(){
        return resourceBundle.getString(TITLED_PANE_EVENT);
    }
}