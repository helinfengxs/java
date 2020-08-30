package com.helinfeng.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkDownUtil {

    /**
     * markdown转换成HTML格式
     * @param markdown
     * @return
     */
    public static  String markDownToHtml(String markdown){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    public static  String markDownToTtmlExtensions(String mardown){
        //h标题生成id
        Set<Extension>  headindAnchorExtension = Collections.singleton(HeadingAnchorExtension.create());
        //table转换成Html
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(mardown);



        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headindAnchorExtension)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();

        return renderer.render(document);
    }
    public static class CustomAttributeProvider implements AttributeProvider{
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            if(node instanceof Link){
                map.put("target","_blank");
            }
            if(node instanceof TableBlock){
                map.put("class","ui celled table");
            }
        }
    }
}


