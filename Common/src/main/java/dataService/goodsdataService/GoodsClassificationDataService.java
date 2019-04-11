package dataService.goodsdataService;

import po.GoodsClassificationPO;
import util.ResultMessage;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface GoodsClassificationDataService extends Remote{
	/**
	 * 向序列化文件中添加一个po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage insert(GoodsClassificationPO po) throws RemoteException;

	/**
	 * 根据ID删除
	 * @param ID
	 * @return 处理结果
	 */
	public ResultMessage delete(String ID) throws RemoteException;

	/**
	 * 更新po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage update(GoodsClassificationPO po) throws RemoteException;

	/**
	 * 显示全部po
	 * @return 返回所有po的集合
	 */
	public ArrayList<GoodsClassificationPO> show() throws RemoteException;

	/**
	 * @param id 分类的ID
	 * @return 父类
	 */
	public GoodsClassificationPO getById(String id) throws RemoteException;

}
