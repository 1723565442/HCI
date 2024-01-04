<template>
    <div class="footer">
        <div class="text-container">
            <p class="large-text" style="margin-top: 10px;">@Copyright 南京大学 软件学院 人机交互小组</p>
            <p class="small-text" style="margin-bottom: 10px;">曲廷锌 马志学 王渤元</p>
        </div>
    </div>
</template>
  
<script>
export default {
    name: 'Footer',
    data() {
        return {}
    }
}
</script>
  
<style scoped>
.footer {
    background-color: #171717;
    color: white;
    text-align: center;
    width: 100%;
}

.text-container {
    text-align: center;
}

.large-text {
    padding-top: 13px;
    font-size: 18px;
}

.small-text {
    padding-bottom: 15px;
    font-size: 14px;
}
</style>
  