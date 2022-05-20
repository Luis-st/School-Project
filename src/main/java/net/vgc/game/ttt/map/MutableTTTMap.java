package net.vgc.game.ttt.map;

import net.vgc.game.ttt.TTTType;
import net.vgc.network.buffer.FriendlyByteBuffer;
import net.vgc.util.annotation.DecodingConstructor;

public class MutableTTTMap extends TTTMap {
	
	public MutableTTTMap() {
		super();
	}
	
	public MutableTTTMap(TTTType topLeftType, TTTType topCenterType, TTTType topRightType, TTTType midLeftType, TTTType midCenterType, TTTType midRightType, TTTType bottomLeftType, TTTType bottomCenterType, TTTType bottomRightType) {
		super(topLeftType, topCenterType, topRightType, midLeftType, midCenterType, midRightType, bottomLeftType, bottomCenterType, bottomRightType);
	}
	
	@DecodingConstructor
	private MutableTTTMap(FriendlyByteBuffer buffer) {
		super();
		this.topLeftType = buffer.readEnum(TTTType.class);
		this.topCenterType = buffer.readEnum(TTTType.class);
		this.topRightType = buffer.readEnum(TTTType.class);
		this.midLeftType = buffer.readEnum(TTTType.class);
		this.midCenterType = buffer.readEnum(TTTType.class);
		this.midRightType = buffer.readEnum(TTTType.class);
		this.bottomLeftType = buffer.readEnum(TTTType.class);
		this.bottomCenterType = buffer.readEnum(TTTType.class);
		this.bottomRightType = buffer.readEnum(TTTType.class);
	}
	
	public void setTopLeftType(TTTType topLeftType) {
		this.topLeftType = topLeftType;
	}
	
	public void setTopCenterType(TTTType topCenterType) {
		this.topCenterType = topCenterType;
	}
	
	public void setTopRightType(TTTType topRightType) {
		this.topRightType = topRightType;
	}
	
	public void setMidLeftType(TTTType midLeftType) {
		this.midLeftType = midLeftType;
	}
	
	public void setMidCenterType(TTTType midCenterType) {
		this.midCenterType = midCenterType;
	}
	
	public void setMidRightType(TTTType midRightType) {
		this.midRightType = midRightType;
	}
	
	public void setBottomLeftType(TTTType bottomLeftType) {
		this.bottomLeftType = bottomLeftType;
	}
	
	public void setBottomCenterType(TTTType bottomCenterType) {
		this.bottomCenterType = bottomCenterType;
	}
	
	public void setBottomRightType(TTTType bottomRightType) {
		this.bottomRightType = bottomRightType;
	}
	
	public void setType(TTTType type, int vGrid, int hGrid) {
		if (vGrid == 0) {
			if (hGrid == 0) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.topLeftType, type);
				this.topLeftType = type;
			} else if (hGrid == 1) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.midLeftType, type);
				this.midLeftType = type;
			} else if (hGrid == 2) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.bottomLeftType, type);
				this.bottomLeftType = type;
			} else {
				LOGGER.warn("Fail to set type for field at map pos {}:{}", vGrid, hGrid);
			}
		} else if (vGrid == 1) {
			if (hGrid == 0) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.topCenterType, type);
				this.topCenterType = type;
			} else if (hGrid == 1) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.midCenterType, type);
				this.midCenterType = type;
			} else if (hGrid == 2) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.bottomCenterType, type);
				this.bottomCenterType = type;
			} else {
				LOGGER.warn("Fail to set type for field at map pos {}:{}", vGrid, hGrid);
			}
		} else if (vGrid == 2) {
			if (hGrid == 0) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.topRightType, type);
				this.topRightType = type;
			} else if (hGrid == 1) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.midRightType, type);
				this.midRightType = type;
			} else if (hGrid == 2) {
				LOGGER.debug("Update field at map pos {}:{} from {} to {}", vGrid, hGrid, this.bottomRightType, type);
				this.bottomRightType = type;
			} else {
				LOGGER.warn("Fail to set type for field at map pos {}:{}", vGrid, hGrid);
			}
		} else {
			LOGGER.warn("Fail to set type for field at map pos {}:{}", vGrid, hGrid);
		}
	}
	
	public void reset() {
		this.topLeftType = TTTType.NO;
		this.topCenterType = TTTType.NO;
		this.topRightType = TTTType.NO;
		this.midLeftType = TTTType.NO;
		this.midCenterType = TTTType.NO;
		this.midRightType = TTTType.NO;
		this.bottomLeftType = TTTType.NO;
		this.bottomCenterType = TTTType.NO;
		this.bottomRightType = TTTType.NO;
		LOGGER.info("Reset the field map to it's default");
	}
	
	public TTTMap immutable() {
		return new TTTMap(this.topLeftType, this.topCenterType, this.topRightType, this.midLeftType, this.midCenterType, this.midRightType, this.bottomLeftType, this.bottomCenterType, this.bottomRightType);
	}
	
}
