class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int deliveryPointer = n - 1;
        int pickupPointer = n - 1;


        while (deliveryPointer >= 0 || pickupPointer >= 0) {
            int maxDistance = -1;

            int deliveryCap = 0;
            int pickupCap = 0;

            while (deliveryPointer >= 0 && deliveryCap < cap) {
                if (deliveries[deliveryPointer] > 0) {
                    maxDistance = Math.max(maxDistance, deliveryPointer);
                    if (deliveries[deliveryPointer] + deliveryCap > cap) {
                        deliveries[deliveryPointer] -= (cap - deliveryCap);
                        deliveryCap = cap;
                    } else {
                        deliveryCap += deliveries[deliveryPointer];
                        deliveries[deliveryPointer] = 0;
                    }
                }
                else {
                    deliveryPointer--;
                }
            }

            while (pickupPointer >= 0 && pickupCap < cap) {
                if (pickups[pickupPointer] > 0) {
                    maxDistance = Math.max(maxDistance, pickupPointer);
                    if (pickups[pickupPointer] + pickupCap > cap) {
                        pickups[pickupPointer] -= (cap - pickupCap);
                        pickupCap = cap;
                    } else {
                        pickupCap += pickups[pickupPointer];
                        pickups[pickupPointer] = 0;
                    }
                }
                else {
                    pickupPointer--;
                }
            }
            answer += (maxDistance + 1) * 2;
        }
        return answer;
    }
}