//package Model;
//
//import org.javagram.response.object.UserContact;
//import org.telegram.api.TLUserContact;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
///**
// * Created by Sergey on 27.04.2016.
// */
//public class TestSelfUserImplz extends Contact {
//    public TestSelfUserImplz(String phoneNumber) {
//        super(phoneNumber);
//    }
//
//    @Override
//    public void sendSMSCode() {
//
//    }
//
//    @Override
//    public String getFormatetPhoneNumber() {
//        return "+7 987 505 25-89";
//    }
//
//    @Override
//    public ArrayList<UserContact> getContactsList(boolean forceUpdate) throws IOException {
//        ArrayList<UserContact> userContactArrayList = new ArrayList<>();
//        userContactArrayList.add(new UserContact(new TLUserContact(12545,"User1","LastName1",89875052,"89874534255",null,null)));
//        userContactArrayList.add(new UserContact(new TLUserContact(13455,"User2","LastName2",89875052,"89874534255",null,null)));
//        userContactArrayList.add(new UserContact(new TLUserContact(34566,"User3","LastName3",45345345,"89874534255",null,null)));
//        userContactArrayList.add(new UserContact(new TLUserContact(12235,"User4","LastName4",45345345,"89874534255",null,null)));
//        return null;
//    }
//
//    @Override
//    public boolean isAuth() {
//        return true;
//    }
//
//    @Override
//    public void updateProfile() {
//
//    }
//
//    @Override
//    public Integer getAuthStatus() {
//        return null;
//    }
//
//    @Override
//    public boolean auth(String code) {
//        return true;
//    }
//
//    @Override
//    public void logOut() {
//
//    }
//}
