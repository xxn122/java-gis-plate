<template>
    <div>
      <el-container>
        <el-header class="header">
          <div>
            <div class="wrapper">
              <div class="logo" >
              </div>
              <div class="text">
                智慧校园平台
              </div>
              <div>
                 <el-avatar class="user">
                  user 
                 </el-avatar>
              </div>
              
            </div>
          </div>
        </el-header>
        
      </el-container>
    </div>
    

  <ul class="sidebar">
    <li v-for="(item, index) in sidebar" :key="index" class="sidebar-item" @click="item.visible = !item.visible">{{ item.name }}</li>
  </ul> 
  <div id="map"></div>
  
  <query-tools v-if="sidebar.queryTools.visible"></query-tools>
  <road-planning v-if="sidebar.roadplanning.visible"></road-planning>
  </template>

<script>
import { reactive  } from 'vue'
import { onMounted,provide } from 'vue'

import QueryTools from '@/views/Querytool.vue';
import RoadPlanning from '@/views/RoadPlanning.vue';
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from 'ol/layer/Tile';
import { TileSuperMapRest } from '@supermap/iclient-ol'
import isloaded from '@/Global'
import { useRouter } from 'vue-router'
 
 

export default {
  name: 'App',
  components: {
    QueryTools,
    
    RoadPlanning,
  },
  setup() {
    let sidebar = reactive({
      
      queryTools: {
        name: '查询',
        visible: false,
      },
      roadplanning: {
        name: '路线规划',
        visible: false,
      },
    })
    const router = useRouter()
    let parent = { map: null };
    provide('map', parent);
    const url = 'http://localhost:8090/iserver/services/map-WorkSpace-8/rest/maps/school'
    let map, layers = reactive([])
    onMounted(() => {
      //let isloaded = (inject('isloaded')).isloaded
      if (isloaded.isloaded == false) {
        router.push({ path: '/' }) 
      }
      console.log(isloaded.isloaded)
      // 初始化地图信息
    map = new Map({
    target: 'map',
    view: new View({
      center: [12645084.45 , 4132056.94],
      zoom: 17,
      projection: 'EPSG:3857'
     })
    });
    var layer = new TileLayer({
      source: new TileSuperMapRest({
          url: url,
          wrapX: true
    }),
     projection: 'EPSG:3857'
    });
      parent.map = map
      map.addLayer(layer);
    })
    return {
    sidebar,
    layers,
    }
  },
}

</script>
  <style scoped>
  html,body {
    margin: 0;
    height: 100%;
  }
  .header {
    background-color: rgb(31, 73, 158);
    color: #fff;
    text-align: center;
    height: 100px;
  }
  .wrapper {
  
    margin: 0 auto 20px;
    height: 65px;
    line-height: 65px;
    text-align: center;
  }
  
  .logo{
    margin-top: 15px;
    margin-bottom: 15px;
    background: #000;
    width: 283px;
    background: url('@/assets/topbg.png') no-repeat;
    height: 65px;
    float: left;
  
  }
  .text {
    margin: 25px 0px 5px 30px;
    float: left;
    line-height: 65px;
  }
  .user{
    margin: 25px 0px 5px 30px;
    float: right;
    line-height: 65px;
  }
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    height: 100% auto;
  }
  .panels {
    position: absolute;
    top: 0;
    left: 200px;
    z-index: 999;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  .sidebar {
    float: left;
    z-index: 999;
    height: 100vh;
    width: 100px;
    margin: 0;
    background: rgb(31, 73, 158);
    color: #fff;
  }
  .sidebar-item {
    cursor: pointer;
    transition: .3s;
    padding: 10px 5px;
  }
  .sidebar-item:hover {
    background: blue;
  }
  #map{
	margin: 0px ;padding: 0; width: 93%;height:100vh; float: right ;
 }
 .query {
  position: absolute;
  background: #fff;
  padding: 0%;
  color: #000;
  top: 100px;
  left: 200px;
  z-index: 999;
  border-radius: 2px;
  text-align: right;
  border: 1px solid rgb(87, 87, 87, .6);
}
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
  