# Cesium-Terrain-Builder-Java
cesium terrain builder by java

基于JAVA技术实现DEM转换为Cesium的地形数据

前言：开发者可以轻松的将该应用程序集成到自己的平台应用程序中，形成一整体套的数据转换与应用解决方案。基于JAVA的开发技术路线，可以更便捷的跨平台部署，使得您的平台应用程序更适应未来的云部署方案。源码合作与该邮件联系59135929@qq.com。

这是一个Java应用程序库，旨在创建用于Cesium JavaScript库的地形瓦片。Ceium可以在您的web浏览器中创建交互式3D地球，从而将图像覆盖在底层地形的模型上。Cesium为地形数据提供了许多不同的来源，其中之一是用于CesiumTerrainProvider JavaScript类的高度图数据。

Cesium地形生成器可用于创建位于Cesium地形提供商使用的地形服务器后面的瓦片。请注意，这些工具不提供向浏览器提供这些平铺的方式：相反，Cesium Terrain Server是为提供地形平铺而设计的。特别是Docker大地数据/铯地形服务器图像旨在简化地形瓦片的可视化。

一、数据切片。
Quantized-mesh-1.0是简单多分辨率四叉树金字塔的高度图。它的切片规则和Tile Map Service (TMS) 的global-geodetic规则相似。通过这个切片规则计算与原始光栅分辨率相关的最大、最小缩放级别，并为该最大、最小缩放级别创建地形瓦片，其中平铺范围与光栅范围重叠。
参考资料：https://github.com/CesiumGS/quantized-mesh。

从数字高程模型（DEM）读取经纬度范围和高程值，计算这个范围所对应的切片索引（zoom/x/y）,通过GDAL光栅创建地形图块，填充每个图块的三角形网格，并将生成的图块保存到目录中，所有瓦片都具有后缀名.terrain。

从高程数据到创建三角形网格需要遵循规则，每个图块大小为65x65像素，循环每个象素坐标并从高程数据中采样。
///为具有给定中心的指定正方形生成网格。
///它通过绕四个点逆时针旋转生成方形网格
///三角形象限。
///得到的网格由单个连续三角形条带组成，
///在必要的情况下，通过退化tris转向几个角。

二、数据索引文件layer.json。
在layer.json文件中，需要了解以下几个重要的节点意义。
available：在Cesium加载地形时，会根据缩放级别和瓦片索引请求地形瓦片数据，在请求前，它会检查当前的索引值是否在available的范围中，如果不存在，则不发起请求。
tiles:切片数据访问url结构；
projection:切片数据的投影名称；
scheme:切片数据平铺方案；
version:数据版本；
extension:扩展(是否包括法线和水面)；

三、开发环境。
1、Java jkd 1.8，安装结束后配置相关环境变量；
2、gdal-353-1930-x64-core.msi， 安装结束后需要配置环境变量“JDAL_HOME”变量值为jdal的安装目录。配置结束后在“cmd”窗口中运行gdalinfo，出现提示则安装成功；
3、Java工程类型：maven
