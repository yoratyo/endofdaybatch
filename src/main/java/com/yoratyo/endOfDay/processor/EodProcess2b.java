package com.yoratyo.endOfDay.processor;

import com.yoratyo.endOfDay.model.EodResult;
import org.springframework.batch.item.ItemProcessor;

public class EodProcess2b implements ItemProcessor<EodResult, EodResult> {

    @Override
    public EodResult process(EodResult data) throws Exception {
        double balance = data.getBalance();
        if (balance > 150) {
            balance += 25;
        }
        String thread_2b = Thread.currentThread().getName().substring(13);
        EodResult d = new EodResult(data.getId(), data.getName(), data.getAge(),
                balance, thread_2b, "", data.getPrevious_balance(), data.getAverage_balance(),
                data.getThread_1(), data.getFree_transfer(), data.getThread_2a());

        return d;
    }
}
