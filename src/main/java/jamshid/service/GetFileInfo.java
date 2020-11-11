package jamshid.service;

import jamshid.type.MyMessages;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GetFileInfo {
    public MyMessages fileInfo(Update updt){
        MyMessages mymsg = new MyMessages();
        SendMessage mySendmessage = new SendMessage();
        mySendmessage.setChatId(updt.getMessage().getChatId());
        String photo = "=====Photo Info====";
        String video = "=====Video Info====";
        String audio = "=====Audio Info====";
        String voice = "=====Voice Info====";
        String contact = "====Contact Info====";
        String smaylik = "====Emoji Info====";
        String  anim = "====Animation Info====";
        int width;
        int fileSize;
        int height;
        int duration;
        int userID;
        String user_name;
        String phone;
        String  fileId;
        String emoji;
        String getSetname;

        boolean animated;

        if (updt.getMessage().getPhoto() != null){
            width = updt.getMessage().getPhoto().get(2).getWidth();
            height = updt.getMessage().getPhoto().get(2).getHeight();
            fileSize = updt.getMessage().getPhoto().get(2).getFileSize();
            fileId = updt.getMessage().getPhoto().get(2).getFileId();
            mySendmessage.setText(photo+"\nwidth = " + width + "\nheight = " + height +
                    "\nfileSize = " + fileSize + "\nfileID = " + fileId);
        }
        if (updt.getMessage().getVideo() != null){
            width = updt.getMessage().getVideo().getWidth();
            height = updt.getMessage().getVideo().getHeight();
            fileSize = updt.getMessage().getVideo().getFileSize();
            fileId = updt.getMessage().getVideo().getFileId();
            duration = updt.getMessage().getVideo().getDuration();
            mySendmessage.setText(video+"\nwidth = " + width + "\nheight = " + height +
                    "\nfileSize = " + fileSize + "\nfileID = " + fileId + "\nduration = " +duration);
        }
        if (updt.getMessage().hasAudio()){
            duration = updt.getMessage().getAudio().getDuration();
            fileId = updt.getMessage().getAudio().getFileId();
            fileSize = updt.getMessage().getAudio().getFileSize();

            mySendmessage.setText(audio+"\nfileID = " + fileId + "\nduration = " +duration + "\nfileSize = " + fileSize);
        }
        if (updt.getMessage().hasVoice()){
            duration = updt.getMessage().getVoice().getDuration();
            fileId = updt.getMessage().getVoice().getFileId();
            fileSize = updt.getMessage().getVoice().getFileSize();

            mySendmessage.setText(voice+"\nfileID = " + fileId + "\nduration = " +duration + "\nfileSize = " + fileSize);
        }
        if (updt.getMessage().hasContact()){
            user_name = updt.getMessage().getContact().getFirstName() + " " + updt.getMessage().getContact().getLastName();
            phone = updt.getMessage().getContact().getPhoneNumber();
            userID = updt.getMessage().getContact().getUserID();

            mySendmessage.setText(contact+"\nUser = " + user_name + "\nuserId = " +userID + "\nphoneNumber = " + phone);
        }
        if (updt.getMessage().hasSticker()){
            emoji = updt.getMessage().getSticker().getEmoji();
            fileId = updt.getMessage().getSticker().getFileId();
            width = updt.getMessage().getSticker().getWidth();
            height = updt.getMessage().getSticker().getHeight();
            fileSize = updt.getMessage().getSticker().getFileSize();
            getSetname = updt.getMessage().getSticker().getSetName();
            animated = updt.getMessage().getSticker().getAnimated();

            mySendmessage.setText(smaylik+"\nemoji = " + emoji + "\nanimated = " + animated + "\nfileId = " +fileId + "\nwidth = " + width + "\nheight = " +height + "\nfileSize = " + fileSize + "\nsetName = " + getSetname);
        }
        if (updt.getMessage().hasAnimation()){
            duration = updt.getMessage().getAnimation().getDuration();
            width = updt.getMessage().getAnimation().getWidth();
            fileSize = updt.getMessage().getAnimation().getFileSize();
            height = updt.getMessage().getAnimation().getHeight();
            fileId = updt.getMessage().getAnimation().getFileId();
            getSetname = updt.getMessage().getAnimation().getFileName();

            mySendmessage.setText(anim+"\nname = "+ getSetname +"\nwidth = " + width + "\nheight = " + height +
                    "\nfileSize = " + fileSize + "\nfileID = " + fileId + "\nduration = " +duration);

        }

        mymsg.setSendMessage(mySendmessage);
        mymsg.setTypeMessage("sendmessage");
        return mymsg;
    }
}
