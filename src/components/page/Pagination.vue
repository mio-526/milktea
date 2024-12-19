<template>
  <div>
    <el-config-provider :locale="locale">
      <el-pagination
      v-model:current-page="pageCurrent"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :small="small"
      :disabled="disabled"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    </el-config-provider>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import zhCn from 'element-plus/dist/locale/zh-cn.mjs';

const props = defineProps({
  total: {
    required: true,
    type: Number
  },
  size: {
    type: Number,
    default: 10
  },
  current: {
    type: Number,
    default: 1
  },
  disabled: {
    type: Boolean,
    default: false
  },
  small: {
    type: Boolean,
    default: false
  }
});


const emit = defineEmits(['update:current', 'update:size', 'pagination']);


const locale = ref(zhCn);


const pageCurrent = computed({
  get() {
    return props.current;
  },
  set(val) {
    emit('update:current', val);
  }
});

const pageSize = computed({
  get() {
    return props.size;
  },
  set(val) {
    emit('update:size', val);
  }
});


const handleSizeChange = (val) => {
  emit('pagination', { current: pageCurrent.value, size: val });
};

const handleCurrentChange = (val) => {
  emit('pagination', { current: val, size: pageSize.value });
};
</script>

<style scoped>

</style>
