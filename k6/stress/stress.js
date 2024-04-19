/* stress.js */
import http from 'k6/http';
import { check, group, sleep, fail } from 'k6';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

export let options = {
    stages: [
        { duration: '1m', target: 500 }, // 05초 ~ 10초 동안 VUser 1000
        { duration: '3m', target: 2000 }, // 10초 ~ 15초 동안 VUser 1500
        { duration: '1m', target: 500 }, // 15초 ~ 20초 동안 VUser 2000
    ],
    thresholds: {
        http_req_duration: ['p(95)<100'], // 95% of requests must complete below 100ms
    },
};

const BASE_URL = 'http://localhost:8888';

export default function ()  {
    applyCoupon();
    sleep(1);
};

function applyCoupon(){
    let id = randomIntBetween(1, 100000000);

    var payload = JSON.stringify({
        userId : id
    });

    var params = {
        headers: {
          'Content-Type': 'application/json',
        },
    };

    let applyRes = http.post(`${BASE_URL}/coupons`, payload, params);

    check(applyRes, {
        'applied success': (resp) => resp.status === 200,
    });
}