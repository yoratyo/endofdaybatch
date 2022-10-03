# endofdaybatch

This End Of Day system is using Java Spring Batch framework with this detail steps:

1. Count average balance from current balance and previous balance for every account (Number Thread written in ‘No 1 Thread-No’)

2. User account can get this benefit:
   - If balance 100-150 then get free transfer to 5 (Number Thread written in ‘No 2a Thread-No’)
   - If balance > 150 then get extra 25 balance (Number Thread written in ‘No 2b Thread-No’)

3. Bank have a budget 1.000 and will try to reward 100 first user (data no 1-100) and each get 10 increment (Number Thread written in ‘No 3 Thread-No’).