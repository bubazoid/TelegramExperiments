package View;

import javax.annotation.Resources;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Sergey on 23.04.2016.
 */
public class ResManager {
    private static volatile ResManager instance;
    private static BufferedImage background;
    private static BufferedImage buttonBackground;
    private static BufferedImage buttonSend;
    private static BufferedImage iconBack;
    private static BufferedImage iconClose;
    private static BufferedImage iconEdit;
    private static BufferedImage iconHide;
    private static BufferedImage iconLock;
    private static BufferedImage iconPhone;
    private static BufferedImage iconPlus;
    private static BufferedImage iconSearch;
    private static BufferedImage iconSettings;
    private static BufferedImage iconTrash;
    private static BufferedImage logo;
    private static BufferedImage logoMicro;
    private static BufferedImage logoMini;
    private static BufferedImage maskBlueMini;
    private static BufferedImage maskDarkGrayBig;
    private static BufferedImage maskGray;
    private static BufferedImage maskGrayOnline;
    private static BufferedImage maskWhite;
    private static BufferedImage maskWhiteMini;
    private static BufferedImage maskWhiteOnline;
    private static BufferedImage messageInBottom;
    private static BufferedImage messageInLeft;
    private static BufferedImage messageInTop;
    private static BufferedImage messageOutBottom;
    private static BufferedImage messageOutRight;
    private static BufferedImage messageOutTop;
    private static Font sansLight;
    private static Font sansRegular;
    private static Font sansSemiBold;
    private static Color blueColor = new Color(0x00B2E5);
    private static Color whiteColor = new Color(0xFDFDFD);
    private static Color grayColor = new Color(0xBBBBBB);
    private static Color lightGrayColor = new Color(0xE5E5E5);
    private static Color outMessageColor = new Color(0x4C41AC);
    private static Color inMessageColor = new Color(0x00A7DA);

    //    private ResManager() {`
    static {
        try {
            background = ImageIO.read(ResManager.class.getClass().getResource("/image/background.png"));
        } catch (Exception e) {
            e.printStackTrace(); //Заглушка на случай ошибки: без картинки лучше, чем никак
            background = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            buttonBackground = ImageIO.read(ResManager.class.getClass().getResource("/image/button-background.png"));
        } catch (Exception e) {
            e.printStackTrace(); //Заглушка на случай ошибки: без картинки лучше, чем никак
            buttonBackground = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            logo = ImageIO.read(ResManager.class.getClass().getResource("/image/logo.png"));
        } catch (Exception e) {
            e.printStackTrace();
            logo = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            buttonSend = ImageIO.read(ResManager.class.getClass().getResource("/image/button-send.png"));
        } catch (Exception e) {
            e.printStackTrace();
            buttonSend = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconBack = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-back.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconBack = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconClose = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-close.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconClose = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconEdit = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-edit.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconEdit = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconHide = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-hide.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconHide = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconSettings = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-settings.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconSettings = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconTrash = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-trash.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconTrash = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconLock = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-lock.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconLock = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconPhone = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-phone.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconPhone = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconPlus = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-plus.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconPlus = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            iconSearch = ImageIO.read(ResManager.class.getClass().getResource("/image/icon-search.png"));
        } catch (Exception e) {
            e.printStackTrace();
            iconSearch = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            logo = ImageIO.read(ResManager.class.getClass().getResource("/image/logo.png"));
        } catch (Exception e) {
            e.printStackTrace();
            logo = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            logoMicro = ImageIO.read(ResManager.class.getClass().getResource("/image/logo-micro.png"));
        } catch (Exception e) {
            e.printStackTrace();
            logoMicro = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            logoMini = ImageIO.read(ResManager.class.getClass().getResource("/image/logo-mini.png"));
        } catch (Exception e) {
            e.printStackTrace();
            logoMini = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskBlueMini = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-blue-mini.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskBlueMini = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskDarkGrayBig = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-dark-gray-big.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskDarkGrayBig = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskWhite = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-white.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskWhite = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskWhiteMini = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-white-mini.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskWhiteMini = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskWhiteOnline = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-white-online.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskWhiteOnline = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskGray = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-gray.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskGray = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            maskGrayOnline = ImageIO.read(ResManager.class.getClass().getResource("/image/mask-gray-online.png"));
        } catch (Exception e) {
            e.printStackTrace();
            maskGrayOnline = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            messageInBottom = ImageIO.read(ResManager.class.getClass().getResource("/image/message-in-bottom.png"));
        } catch (Exception e) {
            e.printStackTrace();
            messageInBottom = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            messageInLeft = ImageIO.read(ResManager.class.getClass().getResource("/image/message-in-left.png"));
        } catch (Exception e) {
            e.printStackTrace();
            messageInLeft = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            messageInTop = ImageIO.read(ResManager.class.getClass().getResource("/image/message-in-top.png"));
        } catch (Exception e) {
            e.printStackTrace();
            messageInTop = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            messageOutBottom = ImageIO.read(ResManager.class.getClass().getResource("/image/message-out-bottom.png"));
        } catch (Exception e) {
            e.printStackTrace();
            messageOutBottom = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            messageOutRight = ImageIO.read(ResManager.class.getClass().getResource("/image/message-out-right.png"));
        } catch (Exception e) {
            e.printStackTrace();
            messageOutRight = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            messageOutTop = ImageIO.read(ResManager.class.getClass().getResource("/image/message-out-top.png"));
        } catch (Exception e) {
            e.printStackTrace();
            messageOutTop = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
        try {
            sansLight = Font.createFont(Font.TRUETYPE_FONT, ResManager.class.getClass().getResourceAsStream("/OpenSans/OpenSansLight.ttf"));

        } catch (Exception e) {
            e.printStackTrace();
            sansLight = Font.getFont("Arial");
        }
        try {
            sansRegular = Font.createFont(Font.TRUETYPE_FONT, ResManager.class.getClass().getResourceAsStream("/OpenSans/OpenSansRegular.ttf"));

        } catch (Exception e) {
            e.printStackTrace();
            sansRegular = Font.getFont("Arial");
        }
        try {
            sansSemiBold = Font.createFont(Font.TRUETYPE_FONT, ResManager.class.getClass().getResourceAsStream("/OpenSans/OpenSansSemiBold.ttf"));

        } catch (Exception e) {
            e.printStackTrace();
            sansSemiBold = Font.getFont("Arial");
        }
    }
//
//    public static ResManager getInstance() {
//        ResManager localInstance = instance;
//        if (localInstance == null) {
//            synchronized (ResManager.class) {
//                localInstance = instance;
//                if (localInstance == null) {
//                    instance = localInstance = new ResManager();
//                }
//            }
//        }
//        return localInstance;
//    }

    public static BufferedImage getBackground() {
        return background;
    }

    public static BufferedImage getButtonBackground() {
        return buttonBackground;
    }

    public static BufferedImage getButtonSend() {
        return buttonSend;
    }

    public static BufferedImage getIconBack() {
        return iconBack;
    }

    public static BufferedImage getIconClose() {
        return iconClose;
    }

    public static BufferedImage getIconEdit() {
        return iconEdit;
    }

    public static BufferedImage getIconHide() {
        return iconHide;
    }

    public static BufferedImage getIconLock() {
        return iconLock;
    }

    public static BufferedImage getIconPhone() {
        return iconPhone;
    }

    public static BufferedImage getIconPlus() {
        return iconPlus;
    }

    public static BufferedImage getIconSearch() {
        return iconSearch;
    }

    public static BufferedImage getIconSettings() {
        return iconSettings;
    }

    public static BufferedImage getIconTrash() {
        return iconTrash;
    }

    public static BufferedImage getLogo() {
        return logo;
    }

    public static BufferedImage getLogoMicro() {
        return logoMicro;
    }

    public static BufferedImage getLogoMini() {
        return logoMini;
    }

    public static BufferedImage getMaskBlueMini() {
        return maskBlueMini;
    }

    public static BufferedImage getMaskDarkGrayBig() {
        return maskDarkGrayBig;
    }

    public static BufferedImage getMaskGray() {
        return maskGray;
    }

    public static BufferedImage getMaskGrayOnline() {
        return maskGrayOnline;
    }

    public static BufferedImage getMaskWhite() {
        return maskWhite;
    }

    public static BufferedImage getMaskWhiteMini() {
        return maskWhiteMini;
    }

    public static BufferedImage getMaskWhiteOnline() {
        return maskWhiteOnline;
    }

    public static BufferedImage getMessageInBottom() {
        return messageInBottom;
    }

    public static BufferedImage getMessageInLeft() {
        return messageInLeft;
    }

    public static BufferedImage getMessageInTop() {
        return messageInTop;
    }

    public static BufferedImage getMessageOutBottom() {
        return messageOutBottom;
    }

    public static BufferedImage getMessageOutRight() {
        return messageOutRight;
    }

    public static BufferedImage getMessageOutTop() {
        return messageOutTop;
    }

    public static Font getSansLight() {
        return sansLight;
    }

    public static Font getSansRegular() {
        return sansRegular;
    }

    public static Font getSansSemiBold() {
        return sansSemiBold;
    }

    public static Color getBlueColor() {
        return blueColor;
    }

    public static Color getWhiteColor() {
        return whiteColor;
    }

    public static Color getGrayColor() {
        return grayColor;
    }

    public static Color getLightGrayColor() {
        return lightGrayColor;
    }

    public static Color getOutMessageColor() {
        return outMessageColor;
    }

    public static Color getInMessageColor() {
        return inMessageColor;
    }
}


//
//public class ResManager {
//    private BufferedImage background;
//
//    public ResManager() {
//        try {
//            background = ImageIO.read(new File("res/image/background.png"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public BufferedImage getBackground() {
//        return background;
//    }
//}
