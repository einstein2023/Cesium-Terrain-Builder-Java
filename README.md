# Cesium-Terrain-Builder-Java
cesium terrain builder by java

基于JAVA技术实现DEM转换为Cesium的地形数据  
开发者：59135929@qq.com  

前言：开发者可以轻松的将该应用程序集成到自己的平台应用程序中，形成一整体套的数据转换与应用解决方案。基于JAVA的开发技术路线，可以更便捷的跨平台部署，使得您的平台应用程序更适应未来的云部署方案。

这是一个Java应用程序库，旨在创建用于Cesium JavaScript库的地形瓦片。Ceium可以在您的web浏览器中创建交互式3D地球，从而将图像覆盖在底层地形的模型上。Cesium为地形数据提供了许多不同的来源，开发者可以使用CesiumTerrainProvider类加载当前应用程序生成的地形数据。

一、数据切片
Quantized-mesh-1.0是简单多分辨率四叉树金字塔的高度图。它的切片规则和Tile Map Service (TMS) 的global-geodetic规则相似。通过这个切片规则计算与原始光栅分辨率相关的最大、最小缩放级别，并为该最大、最小缩放级别创建地形瓦片，其中平铺范围与光栅范围重叠。
参考资料：https://github.com/CesiumGS/quantized-mesh

每一个切片都有一个索引（zoom/x/y），通过切片的经纬度范围和缩放级别，对原始高程模型进行仿射变换（transform）和GDAL光栅重采样，生成新的高程图块。图块的大小为65x65像素，循环每个象素坐标并从高程数据中采样。采样后的数据是一个浮点型数组，接下来我将进入到一个关键的环节，即地形网格的创建，好在已经有了地形网格生成工具的代码参考（Delatin），它来自论文《地形和高度场的快速多边形边界》理论，使用Delaunay三角剖分近似高度场，在给定的最大误差下最小化处理点和三角形的数量。

Delatin参考：https://github.com/mapbox/delatin

Delaunay参考：[地形建模及渲染实践 - 知乎](https://zhuanlan.zhihu.com/p/377913642)

地形网格创建以后，开始创建并填充地形瓦片的数据（瓦片中心点、包围球、三角网格顶点与索引等），并将创建的地形瓦片保存到目录中，所有瓦片都具有后缀名.terrain。

二、数据索引文件layer.json  
在layer.json文件中，需要了解以下几个重要的节点意义。  
available：在Cesium加载地形时，会根据缩放级别和瓦片索引请求地形瓦片数据，在请求前，它会检查当前的索引值是否在available的范围中，如果不存在，则不发起请求。  
tiles:切片数据访问url结构；  
projection:切片数据的投影名称；  
scheme:切片数据平铺方案；  
version:数据版本；  
extension:扩展(是否包括法线和水面)；  

三、开发环境  
1、Java jkd 1.8，安装结束后配置相关环境变量；  
2、gdal-353-1930-x64-core.msi， 安装结束后需要配置环境变量“JDAL_HOME”变量值为jdal的安装目录。配置结束后在“cmd”窗口中运行gdalinfo，出现提示则安装成功；  
3、Java工程类型：maven
