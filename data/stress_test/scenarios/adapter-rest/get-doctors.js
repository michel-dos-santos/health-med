import { Trend, Rate } from 'k6/metrics';
import Get from '../common.js';

export let getDuration = new Trend('get_doctors_duration_adapter_rest');
export let getFailRate = new Rate('get_doctors_fail_rate_adapter_rest');
export let getSuccessRate = new Rate('get_doctors_success_rate_adapter_rest');
export let getReqs = new Rate('get_doctors_reqs_adapter_rest');

export default GetCategories => {
    let endpoint = 'http://localhost:8010/api/v1/doctors';
        
    Get(endpoint, getDuration, getReqs, getFailRate, getSuccessRate);
}
