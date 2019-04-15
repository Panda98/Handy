package com.example.handy.core.prefs;

/**
 * @author wangziang
 * @date 2019/04/07
 */
public interface PreferenceHelper {
    /**
     * Set login account
     *
     * @param account Account
     */
    void setLoginAccount(int account);

    /**
     * Set login password
     *
     * @param password Password
     */
    void setLoginPassword(String password);

    /**
     * Get login account
     *
     * @return account
     */
    int getLoginAccount();

    /**
     * Get login password
     *
     * @return password
     */
    String getLoginPassword();

    /**
     * Set login status
     *
     * @param isLogin IsLogin
     */
    void setLoginStatus(boolean isLogin);

    /**
     * Get login status
     *
     * @return login status
     */
    boolean getLoginStatus();

    /**
     * Set cookie
     *
     * @param domain Domain
     * @param cookie Cookie
     */

    /**
     * Set current page
     *
     * @param position Position
     */
    void setCurrentPage(int position);

    /**
     * Get current page
     *
     * @return current page
     */
    int getCurrentPage();

    /**
     * Get no image state
     *
     * @return if has image state
     */
    boolean getNoImageState();

    /**
     * Set no image state
     *
     * @param b current no image state
     */
    void setNoImageState(boolean b);
}
