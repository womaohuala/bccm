package com.cn.bccm.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;


public class EncoderHandler {
	
	 // 解码   
   public void decoderQRCoder(String content, HttpServletResponse response) {   
       try {   
           Reader reader = new MultiFormatReader();   
           String imgPath = "D:\\hwy.png";   
           File file = new File(imgPath);   
           BufferedImage image;   
           try {   
               image = ImageIO.read(file);   
               if (image == null) {   
                   System.out.println("Could not decode image");   
               }   
               LuminanceSource source = new BufferedImageLuminanceSource(image);   
               BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(   
                       source));   
               Result result;   
               Hashtable hints = new Hashtable();   
               hints.put(DecodeHintType.CHARACTER_SET, "GBK");   
               result = new MultiFormatReader().decode(bitmap, hints);   
               String resultStr = result.getText();   
               System.out.println(resultStr);   
 
           } catch (IOException ioe) {   
               System.out.println(ioe.toString());   
           } catch (ReaderException re) {   
               System.out.println(re.toString());   
           }   
 
       } catch (Exception ex) {   
 
       }   
   } 
   
   // 编码   
   public void encodeQRCoder(String content, HttpServletResponse response) {   
       try {   
           //String str = "CN:男;COP:公司;ZW:职务";// 二维码内容   

           BitMatrix byteMatrix;   
           byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes("GBK"),"iso-8859-1"),   
                   BarcodeFormat.QR_CODE, 200, 200);   

           MatrixToImageWriter.writeToStream(byteMatrix, "png", response.getOutputStream());
       } catch (Exception e) {   
           e.printStackTrace();   
       }   
   }   

}
