# 解决设置ClickableSpan后长按冲突的问题

#### 问题描述
3月份修改别人代码的时候想要屏蔽TextView的长按事件，发现TextView有重写OnTouchEvent方法，然后在其中加了长按事件的判断，是长按事件则不做任何处理。结果测试发现并没有得到想要的效果，所以继续查看代码，最终发现，代码里对TextView设置了setSpan(new ClickableSpan)，导致长按事件无法被我们捕捉到。

#### 分析
因为查看代码是因为添加了setSpan(new ClickableSpan)方法导致长按无法被检测到，所以主要是分析ClickableSpan来寻找解决方法。

#### ClickableSpan
If an object of this type is attached to the text of a TextView with a movement method of LinkMovementMethod, the affected spans of text can be selected. If selected and clicked, the {@link #onClick} method will be called.
翻译一下：如果这个类型被添加到TextView的text上，并且这个TextView有一个LinkMovementMethod的行为方法，则text可以被选择。如果选择并且点击，则onClick方法会被调用。
就是说ClickanleSpan被设置到TextView的某段text上，点击被设置的text，则会调用ClickanleSpan的onClick方法。

#### LinkMovementModel
A movement method that traverses links in the text buffer and scrolls if necessary. Supports clicking on links with DPad Center or Enter.
翻译一下：链接文字的行为方法并在需要是滚动。支持DPad中心或输入的链接点击。
查看LInkMovementModel的源码发现，它有一个onTouchEvent方法：


```
@Override
public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
    int action = event.getAction();

    if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= widget.getTotalPaddingLeft();
        y -= widget.getTotalPaddingTop();

        x += widget.getScrollX();
        y += widget.getScrollY();

        Layout layout = widget.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        ClickableSpan[] links = buffer.getSpans(off, off, ClickableSpan.class);

        if (links.length != 0) {
            if (action == MotionEvent.ACTION_UP) {
                links[0].onClick(widget);
            } else if (action == MotionEvent.ACTION_DOWN) {
                Selection.setSelection(buffer,
                    buffer.getSpanStart(links[0]),
                    buffer.getSpanEnd(links[0]));
            }
            return true;
        } else {
            Selection.removeSelection(buffer);
        }
    }

    return super.onTouchEvent(widget, buffer, event);
}
```

查看源码发现，它只是处理了ACTION_UP(抬起)与ACTION_DOWN（按下）事件，里面没有处理长按事件，也没有长按事件的接口。
所以解决方法找到了，就是重写LInkMovementModel的onTouchEvent()方法。

#### 解决方法
写一个LinkMovementClickMethod类，继承LinkMovementMethod类，然后重写onTouchEvent()方法。
将源码中的onTouchEvent代码复制过来，加上一个判断，如果点击不是长按，则按照源码的处理方式处理，代码如下：

```
public class LinkMovementClickMethod extends LinkMovementMethod {

    private long lastClickTime;

    private static final long CLICK_DELAY = 500l;

    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_UP ||
                action == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            x -= widget.getTotalPaddingLeft();
            y -= widget.getTotalPaddingTop();

            x += widget.getScrollX();
            y += widget.getScrollY();

            Layout layout = widget.getLayout();
            int line = layout.getLineForVertical(y);
            int off = layout.getOffsetForHorizontal(line, x);

            ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);

            if (link.length != 0) {
            ============================修改的部分==============================
               if (action == MotionEvent.ACTION_UP) {
                    if (System.currentTimeMillis() - lastClickTime < CLICK_DELAY) {
                        link[0].onClick(widget);
                    }
                } else if (action == MotionEvent.ACTION_DOWN) {
                    Selection.setSelection(buffer,
                            buffer.getSpanStart(link[0]),
                            buffer.getSpanEnd(link[0]));
                    lastClickTime = System.currentTimeMillis();
                }
            ============================修改的部分==============================
                return true;
            } else {
                Selection.removeSelection(buffer);
            }
        }
        return super.onTouchEvent(widget, buffer, event);
    }

    public static LinkMovementClickMethod getInstance() {
        if (null == sInstance) {
            sInstance = new LinkMovementClickMethod();
        }
        return sInstance;
    }

    private static LinkMovementClickMethod sInstance;
}
```

这样长按事件就被排除在外，问题得以解决。
