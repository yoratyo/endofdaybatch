package com.yoratyo.endOfDay.processor;

import com.yoratyo.endOfDay.model.EodResult;
import org.springframework.batch.item.ItemProcessor;

public class EodProcess3 implements ItemProcessor<EodResult, EodResult> {

    @Override
    public EodResult process(EodResult data) throws Exception {
        double balance = data.getBalance();
        if (data.getId() <= 100) {
            balance += 10;
        }
        String thread_3 = Thread.currentThread().getName().substring(13);
        EodResult d = new EodResult(data.getId(), data.getName(), data.getAge(),
                balance, data.getThread_2b(), thread_3, data.getPrevious_balance(), data.getAverage_balance(),
                data.getThread_1(), data.getFree_transfer(), data.getThread_2a());

        return d;
    }
}
