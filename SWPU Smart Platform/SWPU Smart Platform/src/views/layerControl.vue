<template>
  <ul class="list" v-show="all.length > 0">
    <li v-for="(item, index) in all" :key="index">
      <input type="checkbox" v-model="item.visible">
      <label>{{ item.name }}</label>
    </li>
  </ul>
</template>
<script>
import { inject}  from 'vue'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'layermanage',
  props: ['layers'],
  setup() {
    const url = 'http://localhost:8090/iserver/services/map-WorkSpace-8/rest/maps/school'
    
    const newmap = (inject('map')).map
    new ol.supermap.LayerInfoService(url).getLayersInfo(serviceResult => {
      let result = serviceResult.result;
      let i = result.subLayers.layers.length - 1 //设置该图层的显示顺序，0代表最底层，数字越大显示顺序越前。
        let j = 0 //获取地图中的图层的顺序ID
        result.subLayers.layers.map(layer => {
          let sigle = ol.supermap.tiledMapLayer(url, { layersID: '[' + j + ']' })
          layers.push({
            name: layer.name,
            layer: sigle,
            visible: layer.visible,
            zIndex: i,
          })
    })});
  }
  
}
</script>
<style scoped>
.list {
  position: absolute;
  top: 50px;
  left: 200px;
  z-index: 999;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  background: #fff;
  padding: 10px;
  border: 1px solid rgb(87, 87, 87, .6);
}
ul {
  list-style: none;
}
</style>