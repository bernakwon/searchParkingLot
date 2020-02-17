const path = require('path')

const MiniCssExtractPlugin = require('mini-css-extract-plugin')

const baseConfig = function() {
  let settingArgument;

  if(process.env.NODE_ENV === 'production') {
    settingArgument = {
      publicPath: '/',
      assetsDir: "static",
      outputDir: "../src/main/resources/static",
      runtimeCompiler: undefined,
      productionSourceMap: undefined,
      parallel: undefined,
      css: undefined,
      lintOnSave: false,
      configureWebpack:{
        optimization: {
          splitChunks: {
            minSize: 10000,
            maxSize: 250000
          }
        }
      },
      chainWebpack: (config) => {
        console.log("========================== PRODUCTION MODE ==========================")
        config.resolve.alias.set('vue$', 'vue/dist/vue.esm.js')


        //config.optimization.delete('splitChunks')


        /*
        config.output.filename('[name].js')

        config.module
          .rule('images')
          .test(/\.(png|jpe?g|gif|webp)(\?.*)?$/)
          .use('url-loader')
          .loader('url-loader')
          .options({
            limit: 4096,
            name: 'img/[name].[ext]'
          })

        config.module
          .rule('svg')
          .test(/\.(svg)(\?.*)?$/)
          .use('file-loader')
          .loader('file-loader')
          .options({
            name: 'img/[name].[ext]'
          })

        config.module
          .rule('media')
          .test(/\.(mp4|webm|ogg|mp3|wav|flac|aac)(\?.*)?$/)
          .use('url-loader')
          .loader('url-loader')
          .options({
            limit: 4096,
            name: 'media/[name].[ext]'
          })

        config.module
          .rule('fonts')
          .test(/\.(woff2?|eot|ttf|otf)(\?.*)?$/)
          .use('url-loader')
          .loader('url-loader')
          .options({
            limit: 4096,
            name: 'fonts/[name].[ext]'
          })

        config.plugin('extract-css')
          .use(MiniCssExtractPlugin, [{
            filename:'css/[name].css',
            chunkFilename:''
          }])
        */

        /*
        config.plugin('html-index')
          .tap(args => {
            args[0].minify = {
              removeComments: false,
              collapseWhitespace: false,
              removeAttributeQuotes: false,
              collapseBooleanAttributes: false,
              removeScriptTypeAttributes: false
            }

            return args
          })
        */

      }
    }
    //production close
  } else {
    //development start
    settingArgument = {
      devServer: {
        proxy: "http://localhost:7070"
      },
      publicPath: '/',
      //개발 시에는 outputDir, assetsDir 값을 활용하지 않고 기본값을 활용한다.
      //outputDir: path.posix.join('../src/main/webapp/WEB-INF/views/vue/'),
      //assetsDir: path.posix.join('../../../static/'),
      runtimeCompiler: undefined,
      productionSourceMap: undefined,
      parallel: undefined,
      css: undefined,
      lintOnSave: false,
      configureWebpack:{
        optimization: {
          splitChunks: {
            minSize: 10000,
            maxSize: 250000
          }
        }
      },
      chainWebpack: (config) => {
        //Development Mode
        config.resolve.alias.set('vue$', 'vue/dist/vue.esm.js')

        config.plugin('extract-css')
          .use(MiniCssExtractPlugin, [{
            filename:'[name].[contenthash:8].css',
            chunkFilename:'[name].[contenthash:8].css'
          }])
      }
    }
  } //development close

  return settingArgument
}

module.exports = baseConfig()
