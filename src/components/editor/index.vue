<template>
    <div>
        <QuillEditor ref="myQuillEditor"
          theme="snow"
          v-model:content="content"
          :options="editorOption"
          contentType="html"
        />
        <!-- 使用自定义图片上传 -->
        <el-upload  hidden accept=".jpg,.png" ref="fileBtn" action="http://127.0.0.1:8888/api/upload" :on-change="handleChange" :on-remove="handleRemove" :on-success="handleSuccess" />
    </div>
</template>

<script>
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css'
export default {
    components: { QuillEditor },
    props:{
        modelValue:{
            type: String,
        }
    },
    data() {
        return {
            editorOption: {
                modules: {
                    toolbar: [
                        ['bold', 'italic', 'underline', 'strike'],
                        [{ 'size': ['small', false, 'large', 'huge'] }],
                        [{ 'font': [] }],
                        [{ 'align': [] }],
                        [{ 'list': 'ordered' }, { 'list': 'bullet' }],
                        [{ 'indent': '-1' }, { 'indent': '+1' }],
                        [{ 'header': 1 }, { 'header': 2 }],
                        ['image'],
                        [{ 'direction': 'rtl' }],
                        [{ 'color': [] }, { 'background': [] }]
                    ]
                },
                placeholder: '请输入内容...'
            },
            myQuillEditor: null,
            content: ''
        }
    },
    mounted(){
      this.myQuillEditor = this.$refs['myQuillEditor'];
      let quill = this.myQuillEditor.getQuill()
      quill.getModule('toolbar').addHandler('image', this.imgHandler)
    },
    methods:{
      imgHandler(){
        let fileBtn = this.$refs['fileBtn']
        fileBtn.$el.querySelector('input').click()
      },
      handleSuccess(res,file,fileList){
        if(res.code === 200){
          let quill = this.myQuillEditor.getQuill()
          let range = quill.getSelection()
          let url = '/api' + res.data
          quill.insertEmbed(range.index, 'image', url)
          quill.setSelection(range.index + 1)
        }
      }
    },
    watch:{
      modelValue:function(newValue,oldValue){
          if(newValue + '' == 'undefined'){
            this.myQuillEditor.setHTML("")
          }else{
            this.myQuillEditor.setHTML(newValue)
          }
      }
    },
    computed:{
        content:{          
            set(val){
                this.$emit('update:modelValue',val)
            }
        }
    }
}

</script>

<style scoped>
:deep(.ql-editor) {
    min-height: 180px;
}

:deep(.ql-formats) {
    height: 21px;
    line-height: 21px;
}
</style>

