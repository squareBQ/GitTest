package com.github.zahileoo.widget.signview;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.github.zahileoo.gittest.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by zahi on 2017/6/20.
 */

public class SignCalendarView extends LinearLayout {
    private MyGridView gv;
    private LinearLayout ll_title_week;
    public static int signResId = -1;
    public static int unSignResId = -1;
    private static int reSignResId = -1;
    private static boolean isRetroactiveSignStatus = false;
    public static int unSignTextColor = Color.parseColor("#333333");
    public static int signTextColor = Color.parseColor("#ffffff");

    public SignCalendarView(Context context) {
        super(context, null);
    }

    public SignCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        LayoutInflater.from(getContext()).inflate(R.layout.calendar_view, this);
        ll_title_week = findViewById(R.id.ll_title_week);
        gv = findViewById(R.id.gv);
    }

    public SignCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置是否为补签到状态
     *
     * @param isRetroactiveSignStatus
     */
    public void setIsRetroactiveSignStatus(boolean isRetroactiveSignStatus) {
        this.isRetroactiveSignStatus = isRetroactiveSignStatus;
    }

    /**
     * 设置补签到背景
     *
     * @param reSignResId
     */
    public void setReSignResId(int reSignResId) {
        this.reSignResId = reSignResId;
    }

    /**
     * 设置星期title是否显示
     *
     * @param isShow
     */
    public void showWeekTitle(boolean isShow) {
        if (isShow) {
            ll_title_week.setVisibility(VISIBLE);
        } else {
            ll_title_week.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置星期栏的背景
     *
     * @param resID
     */
    public void setWeekTitleBackground(boolean isShow, int resID) {
        if (isShow) {
            ll_title_week.setVisibility(VISIBLE);
            ll_title_week.setBackgroundResource(resID);
        } else {
            ll_title_week.setVisibility(INVISIBLE);
        }
    }

    /**
     * 设置当前日期及签到日期
     *
     * @param date 实际月份为0-11
     */
    public void setDataAndDate(final String date, List<String> days) {
        if (days != null && days.size() > 0) {
            for (int i1 = 0; i1 < days.size(); i1++) {
                String day = days.get(i1);
                if (day.startsWith("0")) {
                    days.set(i1, day.replace("0", ""));
                }
            }
        }
        // 根据当前日期，计算已签到和未签到日期
        List<Model> signedDays = new ArrayList<>();
        final List<Model> unSignedDays = new ArrayList<>();
        String[] split = date.split("-");
        int today = Integer.parseInt(split[2]);
        for (int i = 1; i <= today; i++) {
            String o = String.valueOf(i);
            if (days != null && days.size() > 0 && days.contains(o)) {
                signedDays.add(new Model(o, true));
            } else {
                if (i == today) break;// 今日不算未签到
                unSignedDays.add(new Model(o, false));
            }
        }

        final CalendarViewAdapter adapter = new CalendarViewAdapter(getContext(), date, signedDays, unSignedDays);
        gv.setAdapter(adapter);

        if (!isRetroactiveSignStatus) return;
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(R.id.calendar_text);
                String day = tv.getText().toString();
                if (unSignedDays.size() > 0) {
                    // 遍历未签到日期，重置背景色
                    for (int i = 0; i < unSignedDays.size(); i++) {
                        Model model = unSignedDays.get(i);
                        if (model.getBackgroundResId() == reSignResId) {
                            model.setBackgroundResId(unSignResId);
                            model.setTextColor(unSignTextColor);
                        }
                    }
                } else {
                    return;
                }
                if (!TextUtils.isEmpty(day)) {
                    if (unSignedDays.size() > 0) {
                        for (int i = 0; i < unSignedDays.size(); i++) {
                            Model model = unSignedDays.get(i);
                            if (TextUtils.equals(day, model.getDay())) {
                                model.setBackgroundResId(reSignResId);
                                model.setTextColor(signTextColor);
                                tv.setBackgroundResource(model.getBackgroundResId());
                                tv.setTextColor(model.getTextColor());
                                if (listener != null) {
                                    listener.onUnSignedItemClick(model.getDay());
                                }
                                break;
                            } else {
                                if (listener != null) {
                                    listener.onEmptyClick();
                                }
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        if (listener != null) {
                            listener.onEmptyClick();
                        }
                    }
                }/* else {
                    if (listener != null) {
                        listener.onEmptyClick();
                    }
                }*/
            }
        });
    }

    /**
     * 设置签到和未签到的背景
     *
     * @param signResId
     * @param unSignResId
     */
    public void setMarkOrNotBackgroundResId(int signResId, int unSignResId) {
        this.signResId = signResId;
        this.unSignResId = unSignResId;
    }

    static class CalendarViewAdapter extends BaseAdapter implements ListAdapter {

        private Context mContext;
        private int mDayCount = 42;
        private int mCurrentMonthDayCount;
        private int mCurrent_Week;
        private List<Model> mMarkedDays;
        private List<Model> mUnSignDays;

        public CalendarViewAdapter(Context context, String date, List<Model> markedDays, List<Model> unSignedDays) {
            this.mContext = context;
            if (markedDays != null && markedDays.size() > 0) {
                mMarkedDays = markedDays;
            }
            if (unSignedDays != null && unSignedDays.size() > 0) {
                mUnSignDays = unSignedDays;
            }
            setWholeDaysAndCurrentWeek(date);
        }

        @Override
        public int getCount() {
            return mCurrent_Week > 5 && mCurrentMonthDayCount >= 30 ? mDayCount : mDayCount - 7;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 填充日期
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.calendar_view_item, null);
                holder.tvCalendarDay = convertView.findViewById(R.id.calendar_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            int dayNumber = position - mCurrent_Week + 2;
            if (dayNumber <= mCurrentMonthDayCount && dayNumber > 0) {
                holder.tvCalendarDay.setText(dayNumber + "");

                unSignView(dayNumber, holder);

                mark(dayNumber, holder);
            }

            return convertView;
        }

        /**
         * 根据未签到天数
         *
         * @param dayNumber
         * @param holder    viewholder
         */
        private void unSignView(int dayNumber, ViewHolder holder) {
            if (unSignResId != -1 && mUnSignDays != null && mUnSignDays.size() > 0) {
                for (int i = 0; i < mUnSignDays.size(); i++) {
                    Model model = mUnSignDays.get(i);
                    String day = model.getDay();
                    int dayInt = Integer.parseInt(day);
                    if (dayNumber == dayInt) {
                        holder.tvCalendarDay.setBackgroundResource(model.getBackgroundResId());
                        holder.tvCalendarDay.setTextColor(model.getTextColor());
                    }
                }
            }
        }


        /**
         * 根据签到天数确定mark
         *
         * @param dayNumber
         * @param holder
         */
        private void mark(int dayNumber, ViewHolder holder) {
            if (signResId != -1 && mMarkedDays != null && mMarkedDays.size() > 0) {
                for (int i = 0; i < mMarkedDays.size(); i++) {
                    Model model = mMarkedDays.get(i);
                    String day = model.getDay();
                    int dayInt = Integer.parseInt(day);
                    if (dayNumber == dayInt) {
                        holder.tvCalendarDay.setBackgroundResource(model.getBackgroundResId());
                        holder.tvCalendarDay.setTextColor(model.getTextColor());
                    }
                }
            }
        }

        /**
         * 获取当前月的总天数及第一天的星期数
         * 星期范围：1-7 周日-周六
         * 日期范围：1-31
         * 月份范围：0-11
         *
         * @return
         */
        public void setWholeDaysAndCurrentWeek(String date) {
            if (date != null && date.length() > 0) {
                Calendar calendar = Calendar.getInstance();
                String[] split = date.split("-");
                int year = Integer.parseInt(split[0]);
                int month = Integer.parseInt(split[1]) - 1;
                //int day = Integer.parseInt(split[2]);
                calendar.set(year, month, 1);
                this.mCurrentMonthDayCount = calendar.getActualMaximum(Calendar.DATE); // 获取当前月份的最大的一天
                this.mCurrent_Week = calendar.get(Calendar.DAY_OF_WEEK);
            }
        }

        class ViewHolder {
            TextView tvCalendarDay;
        }
    }

    private OnUnSignedItemClickListener listener;

    public void setOnUnSignedItemClickListener(OnUnSignedItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnUnSignedItemClickListener {
        void onUnSignedItemClick(String selectDay);

        void onEmptyClick();
    }
}
