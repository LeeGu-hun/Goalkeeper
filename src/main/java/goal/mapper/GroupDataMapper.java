package goal.mapper;

import org.apache.ibatis.annotations.Mapper;

import goal.vo.GroupDataVO;

@Mapper
public interface GroupDataMapper {
	public void insertData(GroupDataVO groupData);
}
