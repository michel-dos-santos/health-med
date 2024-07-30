import GetHealthAdapterRest from "./scenarios/adapter-rest/get-healthcheck.js";
import GetDoctorsAdapterRest from "./scenarios/adapter-rest/get-doctors.js";
import { group } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";


export function handleSummary(data) {
  return {
    "summary.html": htmlReport(data),
  };
}

export const options = {
  vus: 20000,
  duration: '60s',
};

export default() =>{

  group('Adapter Rest', () => {
    GetDoctorsAdapterRest();
  });

}