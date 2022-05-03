package SuperLee.BusinessLayer.Agreement;

import SuperLee.BusinessLayer.AgreementItem;

import java.util.List;

public class ByOrderAgreement extends Agreement{

    private int deliveryDays;

    public ByOrderAgreement( int _delivery){
        super();
        deliveryDays = _delivery;
    }

    @Override
    public boolean isTransporting() {
        return true;
    }

    @Override
    public int daysToDelivery() {
        return deliveryDays;
    }

    public int getDeliveryDays(){
        return deliveryDays;
    }

    public void setDeliveryDays(int _delivery){
        deliveryDays = _delivery;
    }
}
