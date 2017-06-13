var chart = AmCharts.makeChart( "citasPorcentaje", {
  "type": "pie",
  "theme": "light",
  "dataProvider": [ {
    "Mes": "Agosto",
    "Pacientes": 12
  }, {
    "Mes": "Septiembre",
    "Pacientes": 16
  }, {
    "Mes": "Octubre",
    "Pacientes": 24
  },{
    "Mes": "Noviembre",
    "Pacientes": 10
  },{
    "Mes": "Diciembre",
    "Pacientes": 30
  } ],
  "valueField": "Pacientes",
  "titleField": "Mes",
   "balloon":{
   "fixedPosition":true
  },
  "export": {
    "enabled": true
  }
} );