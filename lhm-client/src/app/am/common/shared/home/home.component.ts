import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  isUpdatedBarChart: boolean;
  barChartOptions = {
    chart: {
      type: 'column'
    },
    title: { text: 'API VERSION OF THE MOST USED' },
    series: [{
      name: 'Request Number',
      colorByPoint: true,
      data: []
    }],
    yAxis: {
      allowDecimals: false,
      min: 0,
      title: {
        text: 'Request Number'
      }
    },
    xAxis: {
      type: 'category'
    },
    legend: {
      enabled: false
    },
    credits: {
      enabled: false
    },
    width: null,
    height: null
  };

  ngOnInit() {
  }


}

class BarChartDataItem {
  name: string;
  y: number;
}


