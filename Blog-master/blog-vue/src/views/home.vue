<template>
<div>
  <el-container>
    <el-aside style="width: 25%">
      <div>
        <introduction/>
        <announcement></announcement>
      </div>
    </el-aside>
    <el-main style="width: 50%">
      <indexBlogList></indexBlogList>
    </el-main>
    <el-aside id="aside" style="width: 25%" class="hidden-xs-only">
      <div style="margin: 10px" >
        <hotBlog/>
      </div>
    </el-aside>
  </el-container>
</div>
</template>

<script>
import introduction from '@/components/introduction'
import hotBlog from '@/components/hotBlog'
import announcement from "./announcement";
import indexBlogList from '@/components/indexBlogList'
export default {
  name: "home",
  components: {introduction,hotBlog, announcement, indexBlogList}
}
</script>

<style scoped>

</style>