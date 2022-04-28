package com.efounder.JEnterprise.controller.strongAndWeakElectricityIntegration.common.util;

import com.steadystate.css.parser.CSSOMParser;
import org.apache.log4j.Logger;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName cssUtil
 * @Description
 * @Author GaoGuangChao
 * @Date 2019/9/25 17:01
 * @Version V1.0
 **/
public  class CssParserUtil {
    private final static Logger log = Logger.getLogger(CssParserUtil.class);
    public static List<String> getFonts(String path) throws Exception {
        List<String> fontList=new ArrayList<>();
        String URIPath = "file:///" + path;
        CSSOMParser cssparser = new CSSOMParser();
        CSSStyleSheet css = null;
        try {
            css = cssparser.parseStyleSheet(new InputSource(path), null,
                    null);
        } catch (IOException e) {
            System.out.println("解析css文件异常:" + e);
        }
        if(css!=null){
            CSSRuleList cssrules = css.getCssRules();
            for (int i = 0; i < cssrules.getLength(); i++){
                CSSRule rule = cssrules.item(i);
                if (rule instanceof CSSStyleRule){
                    CSSStyleRule cssrule = (CSSStyleRule) rule;
                    //System.out.println("cssrule.getCssText:"+cssrule.getCssText());
                    String fontName=cssrule.getSelectorText();
                    if(fontName.contains(":before")){
                        fontList.add(fontName.substring(fontName.indexOf(".")+1,fontName.indexOf(":")));
                    }
                    System.out.println("cssrule.getSelectorText:"+fontName);
                    //CSSStyleDeclaration styles=cssrule.getStyle();
                    /*for(int j=0,n=styles.getLength();j<n;j++){
                        System.out.println(styles.item(j)+":"+styles.getPropertyValue(styles.item(j)));
                    }
*/
                }else if (rule instanceof CSSImportRule){
                    CSSImportRule cssrule = (CSSImportRule) rule;
                    System.out.println(cssrule.getHref());
                }
            }
        }else{
                throw new Exception();
        }
        return fontList;
    }
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "\\ARWebapp\\src\\main\\resources\\static\\fonts\\font-awesome\\css\\font-awesome.min.css";
        String URIPath = "file:///" + path;
        try {
            List<String> list=getFonts(URIPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(path);
    }
}
