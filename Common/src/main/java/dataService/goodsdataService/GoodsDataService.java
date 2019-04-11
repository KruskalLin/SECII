package dataService.goodsdataService;

import po.GoodsPO;
import util.ResultMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface GoodsDataService extends Remote{
	/**
	 * 向序列化文件中添加一个po
	 * @param po
	 * @return 处理结果
	 */
	public ResultMessage insert (GoodsPO po) throws RemoteException;

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
	public ResultMessage update(GoodsPO po) throws RemoteException;

	/**
	 * 显示全部po
	 * @return 返回所有po的集合
	 */
	public ArrayList<GoodsPO> show() throws RemoteException;


	/**
	 * 根据keywords查看商品，
	 * @param keywords
	 * @return 商品持久化数据
	 */
	public ArrayList<GoodsPO> selectInEffect(String keywords) throws RemoteException;

	/**
	 * 根据id查找商品
	 * @param id
	 * @return
	 * @throws RemoteException
	 */
	public GoodsPO selectById(String id) throws RemoteException;


}
