package ynu.itcast.demo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MyPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {
        String title = resultItems.get("title").toString();
        System.out.println("定制  title:"+title);
    }
}
