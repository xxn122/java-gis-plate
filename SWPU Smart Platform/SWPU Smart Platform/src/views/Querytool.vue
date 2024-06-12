<template>
    <div class="query">
      <div>
        <label>数据sql查询：</label>
        <input v-model="datasql"/>
        <button @click="dataquery">搜索</button>
      </div>
    </div>
</template>
<script>
import { ref } from 'vue'
import { inject, onUnmounted } from 'vue'
import { GetFeaturesBySQLParameters } from '@supermap/iclient-ol';
import { FeatureService } from '@supermap/iclient-ol';
import VectorSource from 'ol/source/Vector.js';
import VectorLayer from 'ol/layer/Vector.js';
import GeoJSON from 'ol/format/GeoJSON.js';


export default {
    setup() {
      let newmap = (inject('map')).map
        const datasql = ref('学生活动中心')
        let resultLayer =ref()
        const dataurl = "http://localhost:8090/iserver/services/data-WorkSpace-8/rest/data/"
        let dataresultLayer
        function dataquery(){
          resultLayer && newmap.removeLayer(resultLayer)
            if (datasql.value.trim() === '') {
                  return
            }
            var sqlParam = new GetFeaturesBySQLParameters({
            queryParameter: {
                name: "Architecture@Campus",
                attributeFilter: "名称 = '"+ datasql.value+"'",
            },
            datasetNames: ["Campus:Architecture"]
             });

             new FeatureService(dataurl).getFeaturesBySQL(sqlParam).then(function (serviceResult) {
          console.log(serviceResult.result.features)
          var gejson = (new GeoJSON).readFeatures(serviceResult.result.features)
          console.log(gejson)
          var vectorSource = new VectorSource({
                features: gejson,
                wrapX: false
            });
          console.log(vectorSource)
          resultLayer = new VectorLayer({
                source: vectorSource
            });
            console.log(resultLayer)
            newmap.addLayer(resultLayer)
        }); 
        }
        onUnmounted(() => {
          resultLayer && newmap.removeLayer(resultLayer)
        })
        return {
         datasql,
         dataquery,
        }
    }
}
</script>
<style scoped>
.query {
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