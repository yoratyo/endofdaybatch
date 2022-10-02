package com.yoratyo.endOfDay.processor;

import com.yoratyo.endOfDay.model.EodResult;
import org.springframework.batch.item.ItemProcessor;

public class EodProcess2a implements ItemProcessor<EodResult, EodResult> {

    @Override
    public EodResult process(EodResult data) throws Exception {
        int free_transfer = data.getFree_transfer();
        if (data.getBalance() >= 100 && data.getBalance() <= 150) {
            free_transfer = 5;
        }
        String thread_2a = Thread.currentThread().getName().substring(13);
        EodResult d = new EodResult(data.getId(), data.getName(), data.getAge(),
                data.getBalance(), "", "", data.getPrevious_balance(), data.getAverage_balance(),
                data.getThread_1(), free_transfer, thread_2a);

        return d;
    }
}
