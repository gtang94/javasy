package io.github.gtang94.finejar.algorithm;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class SM2 {

    private static final double DEFAULT_EASINESS = 2.5;

    private static final int DEFAULT_INTERVAL = 0;

    private static final int DEFAULT_REPETITIONS = 0;

    // 轻松度（Easiness）：由Quality决定，决定了Interval
    private double easiness;

    // 间隔（interval ）：本次到下次Review的时间间隔(单位：日)
    private int interval;

    // 连续正确次数（repetitions ）：Quality>2次数
    private int repetitions;

    private LocalDate reviewDate;

    public SM2(double easiness, int interval, int repetitions) {
        this.easiness = easiness;
        this.interval = interval;
        this.repetitions = repetitions;
    }

    /**
     * 回答质量（Quality）： 5-0
     * 5 – perfect response
     * 4 – correct response after a hesitation
     * 3 – correct response recalled with serious difficulty
     * 2 – incorrect response; where the correct one seemed easy to recall
     * 1 – incorrect response; the correct one remembered
     * 0 – complete blackout
     */
    public static SM2 first(int quality, LocalDate reviewData) {

        if (Objects.equals(reviewData, null)) {
            reviewData = LocalDate.now();
        }
        SM2 sm2 = new SM2(DEFAULT_EASINESS, DEFAULT_INTERVAL, DEFAULT_REPETITIONS);
        return sm2.next(quality, reviewData);
    }

    public SM2 next(int quality, LocalDate reviewData) {

        if (Objects.equals(reviewData, null)) {
            reviewData = LocalDate.now();
        }

        if (quality < 3) {
            interval = 1;
            repetitions = 0;
        } else {
            if (repetitions == 0) {
                interval = 1;
            } else if (repetitions == 1) {
                interval = 6;
            } else {
                interval = (int) Math.ceil(interval * easiness);
            }
            repetitions++;
        }

        easiness += 0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02);
        if (easiness < 1.3) {
            easiness = 1.3F;
        }

        this.reviewDate = reviewData.plusDays(interval);

        return this;
    }
}
