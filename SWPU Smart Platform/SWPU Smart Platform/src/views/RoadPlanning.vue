<template>
  <div class="plan">
    目前显示的是从南门到北门的路线
    <button @click="findPathProcess">规划</button>
  </div>
</template>
<style>
.plan {
position: absolute;
background: #fff;
padding: 10px;
top: 155px;
left: 200px;
z-index: 999;
border-radius: 5px;
text-align: right;
border: 1px solid rgb(87, 87, 87, .6);
}
</style>
<script>
import Overlay from 'ol/Overlay.js';

import VectorSource from 'ol/source/Vector.js';
import VectorLayer from 'ol/layer/Vector.js';
import GeoJSON from 'ol/format/GeoJSON.js';
import { Fill, Stroke, Style} from 'ol/style.js';
import { ref } from 'vue'
import { inject, onUnmounted } from 'vue';
import { TransportationAnalystResultSetting } from '@supermap/iclient-ol';
import { TransportationAnalystParameter } from '@supermap/iclient-ol';
import { FindPathParameters } from '@supermap/iclient-ol';
import { NetworkAnalystService } from '@supermap/iclient-ol';
export default {
setup(){
  const newmap = (inject('map')).map
  var pathLayer=ref()
  const serviceUrl = "http://localhost:8090/iserver/services/transportationAnalyst-WorkSpace/rest/networkanalyst/Network@Campus";
  function findPathProcess(){
    
    pathLayer && newmap.removeLayer(pathLayer)
    var stopMarker1 =new  Overlay({
         element: document.getElementById('stopMarker1'),
         positioning: 'center-center',
         position: [12645065.107616898, 4132385.715198079]
     });
     var stopMarker2 =new Overlay({
         element: document.getElementById('stopMarker2'),
         positioning: 'center-center',
         position: [12644912.563574411, 4132078.254347635]
     });
     var stopMarker3 =new Overlay({
         element: document.getElementById('stopMarker3'),
         positioning: 'center-center',
         position: [12645219.50863759, 4131808.707515142]
     });
     newmap.addOverlay(stopMarker1);
     newmap.addOverlay(stopMarker2);
     newmap.addOverlay(stopMarker3);
     
     //创建最佳路径分析参数实例
      var resultSetting = new TransportationAnalystResultSetting({
          returnEdgeFeatures: true,
          returnEdgeGeometry: true,
          returnEdgeIDs: true,
          returnNodeFeatures: true,
          returnNodeGeometry: true,
          returnNodeIDs: true,
          returnPathGuides: true,
          returnRoutes: true
      });
      var analystParameter = new TransportationAnalystParameter({
          resultSetting: resultSetting,
          weightFieldName: "xx"
      });
      var findPathParameter = new FindPathParameters({
          isAnalyzeById: false,
          nodes: [{x: 12645065.107616898, y: 4132385.715198079},{x: 12644912.563574411, y: 4132078.254347635},{x: 12645219.50863759, y: 4131808.707515142}],
          hasLeastEdgeCount: false,
          parameter: analystParameter
      });
      console.log(findPathParameter)
      
     new  NetworkAnalystService(serviceUrl).findPath(findPathParameter, function (serviceResult) {
          console.log(serviceResult.result.pathList)
          var routeLayerStyle = new Style({
              stroke: new Stroke({
                  color: 'rgba(100, 100, 225, 10)',
                  width: 3
              }),
              fill: new Fill({
                  color: 'rgba(0, 0, 255, 0.1)'
              })
          });
          serviceResult.result.pathList.map(function (result) {
              
              //添加分析出的路线
              var routeSource = new VectorSource({
                  features: (new GeoJSON).readFeatures(result.route)
              });
               pathLayer = new VectorLayer({
                  source: routeSource,
                  style: routeLayerStyle
              });
              newmap.addLayer(pathLayer);
              
              
          });
      });

  }
  onUnmounted(() => {
    pathLayer && newmap.removeLayer(pathLayer)
        })
  return {
    findPathProcess
  }
}
}


</script>
