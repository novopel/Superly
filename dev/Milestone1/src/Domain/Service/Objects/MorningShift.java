package Domain.Service.Objects;

import Globals.Enums.ShiftTypes;
import Globals.util.HumanInteraction;
import Presentation.Screens.ScreenShiftFactory;

public class MorningShift extends Shift{
    public MorningShift(Domain.Business.Objects.Shift.MorningShift bShift) {
        super(bShift);
    }

    @Override
    public String toString() {
        return "Morning shift: " + date.format(HumanInteraction.dateFormat);
    }

    @Override
    public Presentation.Screens.Shift accept(ScreenShiftFactory screenShiftFactory) {
        return screenShiftFactory.createScreenShift(this);
    }

    @Override
    public ShiftTypes getType() {
        return ShiftTypes.Morning;
    }
}
