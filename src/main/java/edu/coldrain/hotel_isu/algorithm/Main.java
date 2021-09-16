package edu.coldrain.hotel_isu.algorithm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {

        final boolean[] primeCheckList = new boolean[10001];

        final long startTime = System.currentTimeMillis();
        for (int i = 2; i <= 10000; i++) {
            if (!primeCheckList[i]) {
                log.info("에라토스네테스의 체: {}", i);
                for (int j = i; j <= 10000; j *= 2) {
                    primeCheckList[j] = true;
                }
            }
        }
        final long endTime = System.currentTimeMillis();

        log.info("{}", endTime - startTime);
    }
}
