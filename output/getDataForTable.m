clc;clear;
n1=1; n2=10; power_opt=2203; star2_opt=4542;
name = ["jazz","karate","football","as-22july06","hep-th","star","star2","netscience","email","delaunay_n10","power"];
opt = [158,14, 94, 3303, 3926, 6902, 4542, 899, 594, 703, 2203];
for i_name = 1:length(name)
for i=n1:n2
    filename = [char(name(i_name)) '_LS1_600_' num2str(i) '.trace'];
    read = readTrace(filename);
    res{i_name,1}(i-n1+1,1:2)=read(end,1:2);
    res{i_name,1}(i-n1+1,3)=(res{i_name,1}(i-n1+1,2)-opt(i_name))/opt(i_name);
    
    filename = [char(name(i_name))  '_LS2_600_' num2str(i) '.trace'];
    read = readTrace(filename);
    res{i_name,2}(i-n1+1,1:2)=read(end,1:2);
    res{i_name,2}(i-n1+1,3)=(res{i_name,2}(i-n1+1,2)-opt(i_name))/opt(i_name);
    
end

end
% time vc error
for i=1:length(name)
    for j = 1:2
        table(i,(3*j-2):3*j)=[mean(res{i,j})];
    end
end