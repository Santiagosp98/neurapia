var chartData = generateChartData();

var chart = AmCharts.makeChart("citas", {
    "type": "serial",
    "theme": "light",
    "legend": {
        "useGraphSettings": true
    },
    "dataProvider": chartData,
    "synchronizeGrid":true,
    "valueAxes": [{
        "id":"v1",
        "axisColor": "#3DCCC2",
        "axisThickness": 2,
        "axisAlpha": 1,
        "position": "left"
    }, {
        "id":"v2",
        "axisColor": "#FCD202",
        "axisThickness": 2,
        "axisAlpha": 1,
        "position": "right"
    }, {
        "id":"v3",
        "axisColor": "#ff0000",
        "axisThickness": 2,
        "gridAlpha": 0,
        "offset": 50,
        "axisAlpha": 1,
        "position": "left"
    }, {
        "id":"v4",
        "axisColor": "#F83A86",
        "axisThickness": 2,
        "gridAlpha": 0,
        "offset": 50,
        "axisAlpha": 1,
        "position": "right"
    }],
    "graphs": [{
        "valueAxis": "v1",
        "lineColor": "#3DCCC2",
        "bullet": "round",
        "bulletBorderThickness": 1,
        "hideBulletsCount": 30,
        "title": "Total Citas",
        "valueField": "visits",
        "fillAlphas": 0
    }, {
        "valueAxis": "v2",
        "lineColor": "#FCD202",
        "bullet": "square",
        "bulletBorderThickness": 1,
        "hideBulletsCount": 30,
        "title": "Pacientes atendidos",
        "valueField": "hits",
        "fillAlphas": 0
    }, {
        "valueAxis": "v3",
        "lineColor": "#ff0000",
        "bullet": "triangleUp",
        "bulletBorderThickness": 1,
        "hideBulletsCount": 30,
        "title": "Citas Canceladas",
        "valueField": "views",
        "fillAlphas": 0
    }, {
        "valueAxis": "v4",
        "lineColor": "#F83A86",
        "bullet": "triangleDown",
        "bulletBorderThickness": 1,
        "hideBulletsCount": 30,
        "title": "Citas Aplazadas",
        "valueField": "registrado",
        "fillAlphas": 0
    }],
    "chartScrollbar": {},
    "chartCursor": {
        "cursorPosition": "mouse"
    },
    "categoryField": "date",
    "categoryAxis": {
        "parseDates": true,
        "axisColor": "#DADADA",
        "minorGridEnabled": true
    },
    "export": {
        "enabled": true,
        "position": "bottom-right"
     }
});

chart.addListener("dataUpdated", zoomChart);
zoomChart();


// generate some random data, quite different range
function generateChartData() {
    var chartData = [];
    var firstDate = new Date();
    firstDate.setDate(firstDate.getDate() -150);

    for (var i = 0; i < 5; i++) {
        // we create date objects here. In your data, you can have date strings
        // and then set format of your dates using chart.dataDateFormat property,
        // however when possible, use date objects, as this will speed up chart rendering.
        // var newDate = new Date(firstDate);
        // newDate.setDate(newDate.getDate() + i);

        // var visits =0;
        // var hits = 0.96;
        // var views = 6000;

        // chartData.push({
        //     date: newDate,
        //     visits: visits,
        //     hits: hits,
        //     views: views
        // });
    }

    var newDate = new Date(firstDate);
    newDate.setDate(newDate.getDate()+4);

        var Activos =19;
        var hits =  12;
        var views = 4;
        var registrado =3;
        
        chartData.push({
            date: "2017-08-02",
            visits: Activos,
            hits: hits,
            views: views,
            registrado: registrado
            
        });

    var newDate = new Date(firstDate);
    newDate.setDate(newDate.getDate()+2);

        var visits =23;
        var hits = 16;
        var views = 5;
        var registrado =2;
        

        chartData.push({
            date: "2017-09-02",
            visits: visits,
            hits: hits,
            views: views,
            registrado: registrado
            
        });

    var newDate = new Date(firstDate);
    newDate.setDate(newDate.getDate()+2);

        var visits =33;
        var hits = 24;
        var views = 3;
        var registrado =6;

        chartData.push({
            date: "2017-10-02",
            visits: visits,
            hits: hits,
            views: views,
            registrado: registrado
            
        });

    var newDate = new Date(firstDate);
    newDate.setDate(newDate.getDate()+2);

        var visits =12;
        var hits = 10;
        var views = 1;
        var registrado =1;

        chartData.push({
            date: "2017-11-02",
            visits: visits,
            hits: hits,
            views: views,
            registrado: registrado
           
        });

    var newDate = new Date(firstDate);
    newDate.setDate(newDate.getDate()+2);

        var visits =39;
        var hits = 30;
        var views = 1;
        var registrado =8;

        chartData.push({
            date: "2017-12-02",
            visits: visits,
            hits: hits,
            views: views,
            registrado: registrado
            
        });

    return chartData;
}

// function zoomChart(){
//     chart.zoomToIndexes(chart.dataProvider.length - 20, chart.dataProvider.length - 1);
// }