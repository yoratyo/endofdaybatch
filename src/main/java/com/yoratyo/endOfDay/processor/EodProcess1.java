package com.yoratyo.endOfDay.processor;

import com.yoratyo.endOfDay.model.Eod;
import com.yoratyo.endOfDay.model.EodResult;
import org.springframework.batch.item.ItemProcessor;

public class EodProcess1 implements ItemProcessor<Eod, EodResult> {

    @Override
    public EodResult process(Eod data) throws Exception {
        double average_balance = (data.getBalance() +  data.getPrevious_balance()) / 2;
        String thread_1 = Thread.currentThread().getName().substring(13);
        EodResult d = new EodResult(data.getId(), data.getName(), data.getAge(),
                data.getBalance(), "", "", data.getPrevious_balance(), average_balance,
                thread_1, data.getFree_transfer(), "");

        return d;
    }
}
