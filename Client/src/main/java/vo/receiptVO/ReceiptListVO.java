package vo.receiptVO;

import vo.abstractVO.SelectableVO;

public abstract class ReceiptListVO<TL> extends SelectableVO<TL> {
    public abstract <TV extends ReceiptVO> TV toVO();
}
