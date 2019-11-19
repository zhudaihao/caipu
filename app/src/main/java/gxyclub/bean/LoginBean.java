package gxyclub.bean;

/**
 *
 */

public class LoginBean {

    private String hasRiskTest;//是否已参与风险测评 0-否 1-是
    private String token;
    private String inviteUrl;
    private UserBean user;
    private DisplayBean display;

    private String userType = "";//用户类型：1-个人用户， 2-企业用户
    private String riskTestUrl;//风险评测

    private WithdrawRate withdrawRate;

    private String isRiskTestMust;//是否强制评测

    public String getIsRiskTestMust() {
        return isRiskTestMust;
    }

    public void setIsRiskTestMust(String isRiskTestMust) {
        this.isRiskTestMust = isRiskTestMust;
    }

    public WithdrawRate getWithdrawRate() {
        return withdrawRate;
    }

    public void setWithdrawRate(WithdrawRate withdrawRate) {
        this.withdrawRate = withdrawRate;
    }

    public String getHasRiskTest() {
        return hasRiskTest;
    }

    public void setHasRiskTest(String hasRiskTest) {
        this.hasRiskTest = hasRiskTest;
    }

    public String getRiskTestUrl() {
        return riskTestUrl;
    }

    public void setRiskTestUrl(String riskTestUrl) {
        this.riskTestUrl = riskTestUrl;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public DisplayBean getDisplay() {
        return display;
    }

    public void setDisplay(DisplayBean display) {
        this.display = display;
    }

    public static class UserBean {

       /* private String userName;
        private String graphPwdEnable;
        private String name;
        private String mobilePhone;
        private String idCard;
        private String otherUname;
        private String photoUrl;
        private String   mobilePhoneShow;*/


        private String userName;
        private String name;
        private String mobilePhone;
        private String idCard;
        private String otherUname;
        private String photoUrl;
        private String graphPwdEnable;
        private String mobilePhoneShow;

        private String nameShow;
        private String idCardShow;
        private int confirm;
        private int cardType;//1:国内身份证 2:护照 3:港澳台同胞回乡证 4外国人永久居留证


        public String getNameShow() {
            return nameShow;
        }

        public void setNameShow(String nameShow) {
            this.nameShow = nameShow;
        }

        public String getIdCardShow() {
            return idCardShow;
        }

        public void setIdCardShow(String idCardShow) {
            this.idCardShow = idCardShow;
        }

        public int getConfirm() {
            return confirm;
        }

        public void setConfirm(int confirm) {
            this.confirm = confirm;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getMobilePhoneShow() {
            return mobilePhoneShow;
        }

        public void setMobilePhoneShow(String mobilePhoneShow) {
            this.mobilePhoneShow = mobilePhoneShow;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getGraphPwdEnable() {
            return graphPwdEnable;
        }

        public void setGraphPwdEnable(String graphPwdEnable) {
            this.graphPwdEnable = graphPwdEnable;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getOtherUname() {
            return otherUname;
        }

        public void setOtherUname(String otherUname) {
            this.otherUname = otherUname;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }
    }

    public static class DisplayBean {


        private String investorModule;
        private String investorBankState;
        private String investorBorrowMenu;
        private String borrowerModule;
        private String borrowerBankState;
        private String guaranteeModule;
        private String guaranteeBankState;


        public String getInvestorModule() {
            return investorModule;
        }

        public void setInvestorModule(String investorModule) {
            this.investorModule = investorModule;
        }

        public String getInvestorBankState() {
            return investorBankState;
        }

        public void setInvestorBankState(String investorBankState) {
            this.investorBankState = investorBankState;
        }

        public String getInvestorBorrowMenu() {
            return investorBorrowMenu;
        }

        public void setInvestorBorrowMenu(String investorBorrowMenu) {
            this.investorBorrowMenu = investorBorrowMenu;
        }

        public String getBorrowerModule() {
            return borrowerModule;
        }

        public void setBorrowerModule(String borrowerModule) {
            this.borrowerModule = borrowerModule;
        }

        public String getBorrowerBankState() {
            return borrowerBankState;
        }

        public void setBorrowerBankState(String borrowerBankState) {
            this.borrowerBankState = borrowerBankState;
        }

        public String getGuaranteeModule() {
            return guaranteeModule;
        }

        public void setGuaranteeModule(String guaranteeModule) {
            this.guaranteeModule = guaranteeModule;
        }

        public String getGuaranteeBankState() {
            return guaranteeBankState;
        }

        public void setGuaranteeBankState(String guaranteeBankState) {
            this.guaranteeBankState = guaranteeBankState;
        }
    }


    public static class WithdrawRate {
        private String createTime;
        private String dataSource;
        private String deleteTime;
        private String id;
        private String isUse;
        private String minMoney;
        private String onceMaxMoney;
        private String operationUser;
        private String rule;
        private String ruleType;
        private String type;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public String getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(String deleteTime) {
            this.deleteTime = deleteTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIsUse() {
            return isUse;
        }

        public void setIsUse(String isUse) {
            this.isUse = isUse;
        }

        public String getMinMoney() {
            return minMoney;
        }

        public void setMinMoney(String minMoney) {
            this.minMoney = minMoney;
        }

        public String getOnceMaxMoney() {
            return onceMaxMoney;
        }

        public void setOnceMaxMoney(String onceMaxMoney) {
            this.onceMaxMoney = onceMaxMoney;
        }

        public String getOperationUser() {
            return operationUser;
        }

        public void setOperationUser(String operationUser) {
            this.operationUser = operationUser;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getRuleType() {
            return ruleType;
        }

        public void setRuleType(String ruleType) {
            this.ruleType = ruleType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}

