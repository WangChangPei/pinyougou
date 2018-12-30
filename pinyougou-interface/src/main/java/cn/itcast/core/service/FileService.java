package cn.itcast.core.service;

import java.io.IOException;
import java.util.List;

public interface FileService {


    byte[] exportGoods() throws IOException;

    byte[] exportOrder() throws Exception;

  void updateFileBrand(List list) throws Exception;
}
