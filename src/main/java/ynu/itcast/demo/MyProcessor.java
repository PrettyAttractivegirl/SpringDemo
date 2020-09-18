package ynu.itcast.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/*爬取类*/
public class MyProcessor implements PageProcessor {
    public void process(Page page) {
       /* 打印页面内容
        System.out.println(page.getHtml().toString());
        page.addTargetRequests(page.getHtml().links().all());*/
        page.addTargetRequests((page.getHtml().links().regex("https://blog.csdn.net/[a-z 0-9 -]+/article/details/[0-9]{9}").all()));
//        System.out.println(page.getHtml().xpath("//*[@id=\"nav\"]/div/div/ul/li[5]/a").toString());
//        System.out.println(page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1 "));

        page.putField("title",page.getHtml().xpath("//*[@id=\"mainBox\"]/main/div[1]/div/div/div[1]/h1 "));
    }

    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

    public static void main(String[] args) {
        Spider.create(new MyProcessor())
                .addUrl("https://www.csdn.net/")
                .addPipeline(new ConsolePipeline())
                /*.addPipeline(new FilePipeline(""))
                .addPipeline(new JsonFilePipeline(""))*/
                .addPipeline(new MyPipeline())
                .run();
    }
}
