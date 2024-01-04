<script setup>
import { onMounted, onUnmounted } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';

let map = null;

onMounted(() => {
  AMapLoader.load({
    key: "21410e8154ca3fe8feaa2141fd4f4b02", // 你的高德地图API Key
    version: "2.0",
    plugins: [],
  })
    .then((AMap) => {
      map = new AMap.Map("container", {
        viewMode: "3D",
        zoom: 15,
        center: [114.77943, 32.055015],
      });
    })
    .catch((e) => {
      console.error(e);
    });
});

onUnmounted(() => {
  if (map) {
    map.destroy();
  }
});
</script>

<template>
  <div id="container"></div>
</template>

<style scoped>
#container {
  width: 65vw;
  height: 65vh;
}
</style>
