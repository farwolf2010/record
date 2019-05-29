//
//  RecordModule.m
//  AFNetworking
//
//  Created by 郑江荣 on 2019/1/9.
//

#import "RecordModule.h"
#import "NatRecorder.h"
#import "farwolf_weex.h"
#import <WeexPluginLoader/WeexPluginLoader.h>
@implementation RecordModule
WX_PlUGIN_EXPORT_MODULE(record, RecordModule)
@synthesize weexInstance;
WX_EXPORT_METHOD(@selector(start:))
WX_EXPORT_METHOD(@selector(pause))
WX_EXPORT_METHOD(@selector(stop:))


-(void)start:(NSMutableDictionary*)dic
{
    NSString *quality=@"low";
    if([dic objectForKey:@"quality"])
     quality=dic[@"quality"];
    NSString *channel=@"mono";
    if([dic objectForKey:@"channel"])
        channel=dic[@"channel"];
    
    [[NatRecorder singletonManger] start:@{@"quality":quality,@"channel":channel} :^(id error, id result) {
        
    }];
}
-(void)pause
{
    [[NatRecorder singletonManger] pause:^(id error, id result) {
        
    }];
}
-(void)stop:(WXModuleCallback)callback
{
    [[NatRecorder singletonManger] stop:^(id error, id result) {
        NSMutableDictionary *dic=result;
        NSString *path= [PREFIX_SDCARD add: dic[@"path"]];
        callback(@{@"path":path});
    }];
}
@end
