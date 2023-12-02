package com.labelzoom.api.model.components;

public interface IAddressBlock extends IFontComponent
{
    boolean isUseLocalizedAddressFormat();
    void setUseLocalizedAddressFormat(boolean useLocalizedAddressFormat);

    int getStaticAddressFormat();
    void setStaticAddressFormat(int addressFormat);

    boolean isShowContactName();
    void setShowContactName(boolean showContactName);

    boolean isShowCountry();
    void setShowCountry(boolean showCountry);

    String getAddressId();
    void setAddressId(String addressId);
}
